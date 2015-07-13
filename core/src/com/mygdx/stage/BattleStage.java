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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.model.GridHitbox;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.Unit;
import com.mygdx.state.StaticAssets;

public class BattleStage extends BaseOneLevelStage {
	// Table
	private Stack tableStack; // 전체 테이블을 포함하는 테이블 스택
	private Table uiTable; // 화면 전체 테이블
	private Table orderTable; // 순서를 나타내는 테이블
	// private Table gridTable; // grid 테이블
	private Table tileTable;
	private Table RMenuTable; // 오른쪽 테이블

	// Grid Tabler관련
	private boolean gridFlag;
	private float gridPadTop; // grid 테이블의 y위치

	private GridHitbox gridHitbox;

	// Value
	private float RButtonWidth;
	private float RButtonHeight;

	// Button
	private ImageButton attackButton;
	private ImageButton skillButton;
	private ImageButton inventoryButton;
	private ImageButton defenseButton;
	private ImageButton waitButton;
	private ImageButton escapeButton;

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

	public BattleStage() {
		Gdx.app.debug("BattleStage", "Constructor() call");
	}

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

	public Stage makeStage() {
		Gdx.app.debug("BattleStage", "makeStage(Rm rm)");

		resolutionWork();

		monster = movingInfo.getSelectedMonster();

		makeFirstOrder();

		makeAllTable();

		updateOrderTable();

		return this;
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
		RButtonHeight = StaticAssets.windowHeight * 0.1278f;
		RButtonWidth = RButtonHeight;
		gridPadTop = StaticAssets.windowHeight * 0.125f;

		// FIXME 상수 대신 monster 타입에 따라 다른 크기 사용해야 함
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

	private void makeRMenuTable() {
		RMenuTable = new Table();

		RMenuTable.right().bottom();
		RMenuTable.padBottom(StaticAssets.windowHeight * 0.013f).padRight(
				StaticAssets.windowWidth * 0.007f);
		RMenuTable.setFillParent(true);
		RMenuTable.add(attackButton).width(RButtonWidth).height(RButtonHeight);
		RMenuTable.row();
		RMenuTable.add().height(StaticAssets.windowHeight * 0.0138f);
		RMenuTable.row();
		RMenuTable.add(skillButton).width(RButtonWidth).height(RButtonHeight);
		RMenuTable.row();
		RMenuTable.add().height(StaticAssets.windowHeight * 0.0138f);
		RMenuTable.row();
		RMenuTable.add(inventoryButton).width(RButtonWidth)
				.height(RButtonHeight);
		RMenuTable.row();
		RMenuTable.add().height(StaticAssets.windowHeight * 0.0138f);
		RMenuTable.row();
		RMenuTable.add(defenseButton).width(RButtonWidth).height(RButtonHeight);
		RMenuTable.row();
		RMenuTable.add().height(StaticAssets.windowHeight * 0.0138f);
		RMenuTable.row();
		RMenuTable.add(waitButton).width(RButtonWidth).height(RButtonHeight);
		RMenuTable.row();
		RMenuTable.add().height(StaticAssets.windowHeight * 0.0138f);
		RMenuTable.row();
		RMenuTable.add(escapeButton).width(RButtonWidth).height(RButtonHeight);

	}

	private void makeTableStack() {
		tableStack = new Stack();
		tableStack.setFillParent(true);
		tableStack.add(uiTable);
		tableStack.add(tileTable);
		tableStack.add(gridHitbox);
		tableStack.add(RMenuTable);
	}

	private void makeAllTable() {
		makeRButton();
		makeGridHitbox();
		makeRMenuTable();
		makeTableStack();

		addListener();

		this.addActor(tableStack);
	}

	private void makeGridHitbox() {
		gridHitbox = new GridHitbox(MonsterEnum.SizeType.MEDIUM);

	}

	private boolean isInsideHitbox(int screenX, int screenY) {
		return true;
	}

	private void updateOrderTable() {
		makeOrder();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (gridFlag && isInsideHitbox(screenX, screenY)) {
			gridHitbox.showTileWhereClicked(screenX, screenY);
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (gridFlag) {
			gridHitbox.showTileWhereMoved(screenX, screenY);
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

			gridHitbox.hideGrid();
		}

		gridHitbox.hideAllTiles();

		return super.touchUp(screenX, screenY, pointer, button);
	}

	public void addListener() {

		// 클릭시 발동
		attackButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				Gdx.app.log("BattleStage", "공격!");
				if (!gridFlag) {
					gridHitbox.showGrid();
				} else {

				}
			}
		});

		skillButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "스킬!");
				gridHitbox.hideGrid();
			}
		});

		inventoryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "인벤토리!");
			}
		});
		defenseButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "방어!");

			}
		});
		waitButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "기다립시다!");

			}
		});
		escapeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "도망!");
				screenFactory.show(ScreenEnum.MOVING);
			}
		});
	}

	private void makeRButton() {

		// 이미지 추가
		attackButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/battle/RMenu_01.png"))));
		skillButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/battle/RMenu_02.png"))));
		inventoryButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/battle/RMenu_03.png"))));
		defenseButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/battle/RMenu_04.png"))));
		waitButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture(
				"texture/battle/RMenu_05.png"))));
		escapeButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/battle/RMenu_06.png"))));

		addListener();

	}

	public BattleManager getBattleManager() {
		return battleManager;
	}

	public void setBattleManager(BattleManager battleManager) {
		this.battleManager = battleManager;
	}

}
