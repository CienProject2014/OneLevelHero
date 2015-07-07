package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.manager.BattleManager;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.Unit;
import com.mygdx.state.StaticAssets;

public class BattleStage extends BaseOneLevelStage {
	// Table
	private Stack tableStack; // 전체 테이블을 포함하는 테이블 스택
	private Table uiTable; // 화면 전체 테이블
	private Table orderTable; // 순서를 나타내는 테이블
	private Table gridTable; // grid 테이블
	private Table tileTable;
	private Table tempTable;

	// Grid Tabler관련
	private boolean gridFlag;
	private float topPadValue; // grid 테이블의 y위치
	private float gridTableWidth; // grid 테이블의 가로 크기
	private float gridTableHeight; // grid 테이블의 세로 크기

	// Tiles
	private Image[][] tileImages;
	private int tileRows;
	private int tileColumns;
	private float tileWidth;
	private float tileHeight;

	// Mouse Event
	private int clickedTileRow;
	private int clickedTileColumn;

	// Value
	private float uiButtonWidth;
	private float uiButtonHeight;

	// Button
	private ImageButton tempButton;

	@Autowired
	private BattleManager battleManager;
	private Monster monster;

	// Unit array
	private ArrayList<Unit> units;
	private Queue<Unit> orderedUnits;

	// Orthographic Camera
	private OrthographicCamera cam;

	// Trigger
	private boolean monsterTrigger;

	@Override
	public void act(float delta) {
		if (gridFlag) {

		} else if (monsterTrigger) {
			Unit actor = getCurrentActor();
			if (!(actor instanceof Monster)) {
				// 일어날 수 없는 시나리오
				// 몬스터의 턴이 아니라면 monsterTrigger가 true여서는 안된다.
				return;
			}
			battleManager.monsterAttack();
			updateOrderTable();

			monsterTrigger = false;
		}

		super.act(delta);
	}

	/**
	 * 입력 좌표가 속한 타일의 인덱스를 배열로 넘긴다.
	 * 
	 * @param screenX
	 *            클릭한 x좌표
	 * @param screenY
	 *            클릭한 y좌표
	 * @return [0]: x인덱스(column), [1]: y인덱스(row)
	 */
	private int[] findTile(int screenX, int screenY) {
		boolean found = false;
		int[] position = new int[2];
		for (int i = 0; i < tileRows && !found; i++) {
			for (int j = 0; j < tileColumns && !found; j++) {
				if (isInsideTile(i, j, screenX, screenY)) {
					position[0] = j;
					position[1] = i;
					found = true;
				}
			}
		}
		if (found) {
			return position;
		} else {
			return null;
		}
	}

	/**
	 * 클릭한 영역의 타일을 보이게 한다.
	 * 
	 * @param screenX
	 * @param screenY
	 */
	private void showTileWhereClicked(int screenX, int screenY) {
		int[] position = findTile(screenX, screenY);
		if (position != null) {
			int i = position[1];
			int j = position[0];
			clickedTileRow = i;
			clickedTileColumn = j;
			tileImages[i][j].setVisible(true);
		}
	}

	private void showTileWhereMoved(int screenX, int screenY) {
		int[] position = findTile(screenX, screenY);
		if (position != null) {
			int i = position[1];
			int j = position[0];
			tileImages[i][j].setVisible(true);
		}
	}

	private void hideAllTiles() {
		for (int i = 0; i < tileRows; i++) {
			for (int j = 0; j < tileColumns; j++) {
				tileImages[i][j].setVisible(false);
			}
		}
	}

	private boolean isInside(float centerX, float centerY, float width,
			float height, int x, int y) {
		int revertedY = (int) (StaticAssets.windowHeight - y);

		if (x > (centerX - width / 2) && x < (centerX + width / 2)
				&& revertedY < (centerY + height / 2)
				&& revertedY > (centerY - height / 2)) {
			return true;
		}

		return false;
	}

	/**
	 * 좌표(x,y)가 (i,j)위치의 타일에 있는지
	 * 
	 * @param i
	 *            타일 행 인덱스
	 * @param j
	 *            타일 열 인덱스
	 * @param x
	 *            x좌표
	 * @param y
	 *            y좌표
	 * @return 타일안에 있으면 true, 아니면 false
	 */
	private boolean isInsideTile(int i, int j, int x, int y) {
		float centerX = tileImages[i][j].getCenterX();
		float centerY = tileImages[i][j].getCenterY();
		float width = tileImages[i][j].getWidth();
		float height = tileImages[i][j].getHeight();

		return isInside(centerX, centerY, width, height, x, y);
	}

	/**
	 * 좌표(x,y)가 Hitbox영역 안에 있는지
	 * 
	 * @param x
	 *            x좌표
	 * @param y
	 *            y좌표
	 * @return 있으면 true, 아니면 false
	 */
	private boolean isInsideHitbox(int x, int y) {
		float centerX = gridTable.getCenterX();
		float centerY = gridTable.getCenterY();
		float width = gridTableWidth;
		float height = gridTableHeight;

		return isInside(centerX, centerY, width, height, x, y);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (gridFlag && isInsideHitbox(screenX, screenY)) {
			showTileWhereClicked(screenX, screenY);
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (gridFlag) {
			showTileWhereMoved(screenX, screenY);
			// TODO clickedTileRow와 Column을 이용해서, 시작점부터 끝점까지 지나는 타일들을 배열로
			// 저장해야한다.
		}
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (gridFlag && isInsideHitbox(screenX, screenY)) {
			Gdx.app.log("BattleStage", "어택!");

			Unit actor = getCurrentActor();

			if (!(actor instanceof Hero)) {
				// 일어날 수 없는 시나리오
				// 만약 몬스터의 턴이라면 이 이벤트가 호출되지 않아야 한다.
			}

			battleManager.userAttack(actor);
			updateOrderTable();

			if (whoIsNextActor() instanceof Monster) {
				monsterTrigger = true;
			}

			hideGrid();
			hideAllTiles();
		} else {
			hideAllTiles();
		}

		return super.touchUp(screenX, screenY, pointer, button);
	}

	public BattleStage() {
		Gdx.app.debug("BattleStage", "Constructor() call");
	}

	private Unit getCurrentActor() {
		Unit unit = orderedUnits.poll();
		orderedUnits.add(unit);
		return unit;
	}

	private Unit whoIsNextActor() {
		Unit unit = orderedUnits.peek();
		return unit;
	}

	private void resolutionWork() {
		uiButtonWidth = StaticAssets.windowWidth * 0.0625f;
		uiButtonHeight = StaticAssets.windowHeight * 0.125f;

		topPadValue = StaticAssets.windowHeight * 0.125f;

		// FIXME 상수 대신 monster 타입에 따라 다른 크기 사용해야 함
		gridTableWidth = StaticAssets.windowWidth * 0.333333f;
		gridTableHeight = gridTableWidth;
	}

	private void tileWork() {
		gridFlag = false;

		// FIXME 5: monster의 타입에 따라서 바뀌어야 함
		tileRows = 5;
		tileColumns = 5;
		tileImages = new Image[tileRows][tileColumns];
		for (int i = 0; i < tileRows; i++) {
			for (int j = 0; j < tileColumns; j++) {
				tileImages[i][j] = getTileImage();
				tileImages[i][j].setVisible(false);
			}
		}
	}

	public Stage makeStage() {
		Gdx.app.debug("BattleStage", "makeStage(Rm rm)");

		resolutionWork();

		monster = movingInfo.getSelectedMonster();

		tileWork();

		makeFirstOrder();

		makeAllTable();

		updateOrderTable();

		return this;
	}

	/**
	 * 전투 참여 유닛들의 순서를 결정
	 */
	private void makeFirstOrder() {
		units = new ArrayList<Unit>(4);
		units.addAll(partyInfo.getPartyList());
		units.add(monster);

		// 행동게이지 초기화
		for (Unit unit : units) {
			unit.setGauge(100);
		}

		// 속도에 따라 정렬
		Collections.sort(units);

		orderedUnits = new LinkedList<Unit>(units);
	}

	private void makeOrder() {
		Collections.sort(units);
	}

	private void makeGridTable() {
		gridTable = new Table();

		gridTable.align(Align.top);
		gridTable.add(getGridImage()) // gridTable에 gridImage 넣는다.
				.padTop(topPadValue) // 상단바에 겹치지 않게 위쪽 Padding(1/8)
				.width(gridTableWidth) // 최대 가로 크기
				.height(gridTableHeight); // 최대 세로 크기
		gridTable.setVisible(false); // 초기에는 보이지 않게 한다.

	}

	private void makeTileTable() {
		tileTable = new Table();
		tileWidth = gridTableWidth / tileColumns;
		tileHeight = gridTableHeight / tileRows;

		tileTable.align(Align.top);
		for (int i = 0; i < tileColumns; i++) {
			tileTable.add(tileImages[0][i]).padTop(topPadValue)
					.width(tileWidth).height(tileHeight);
		}
		for (int i = 1; i < tileRows; i++) {
			tileTable.row();
			for (int j = 0; j < tileColumns; j++) {
				tileTable.add(tileImages[i][j]).width(tileWidth)
						.height(tileHeight);
			}
		}
	}

	private void makeUiTable() {
		uiTable = new Table();
		orderTable = new Table();

		uiTable.setFillParent(true);

		uiTable.align(Align.left);
		uiTable.setWidth(uiButtonWidth);
		uiTable.setY(uiButtonHeight);
	}

	private void makeTempTable() {
		tempTable = new Table();
		tempButton = new ImageButton(new SpriteDrawable(new Sprite(
				getTempTexture(), 50, 50)));

		tempTable.setFillParent(true);

		tempTable.align(Align.right);
		tempTable.add(tempButton);
	}

	private Texture getTempTexture() {
		return new Texture(Gdx.files.internal("texture/battle/grass.png"));
	}

	private void makeTableStack() {
		tableStack = new Stack();
		tableStack.setFillParent(true);
		tableStack.add(uiTable);
		tableStack.add(tileTable);
		tableStack.add(gridTable);
		tableStack.add(tempTable);
	}

	private void makeAllTable() {
		makeUiTable();
		makeGridTable();
		makeTileTable();
		makeTempTable();
		makeTableStack();

		addListener();

		this.addActor(tableStack);
	}

	private void updateOrderTable() {
		uiTable.clear();
		orderTable.clear();

		makeOrder();

		for (Unit unit : orderedUnits) {
			orderTable.add(new Image(unit.getBattleTexture()))
					.width(uiButtonWidth).height(uiButtonHeight);
			orderTable.row();
		}

		uiTable.add(orderTable);
	}

	public void showGrid() {
		gridTable.setVisible(true);
		gridFlag = true;
	}

	public void hideGrid() {
		gridTable.setVisible(false);
		gridFlag = false;
	}

	private void addListener() {
		tempButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!gridFlag) {
					showGrid();
				} else {

				}

			}
		});

	}

	private Image getGridImage() {
		// FIXME medium대신 monster.getType() 사용해야 함.
		return new Image(new Texture(Gdx.files.internal("texture/battle/grid_"
				+ "medium" + ".png")));
	}

	private Image getTileImage() {
		return new Image(new Texture(
				Gdx.files.internal("texture/battle/tile.png")));
	}

	public BattleManager getBattleManager() {
		return battleManager;
	}

	public void setBattleManager(BattleManager battleManager) {
		this.battleManager = battleManager;
	}

}
