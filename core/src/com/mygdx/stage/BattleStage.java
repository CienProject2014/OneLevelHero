package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.GridHitbox;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.StorySectionPacket;
import com.mygdx.model.Unit;

public class BattleStage extends BaseOneLevelStage {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventCheckManager eventCheckManager;
	private final String NORMAL_ATTACK = "normal_attack";

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("BattleStage");
	// Table
	private Table orderTable; // 순서를 나타내는 테이블
	private GridHitbox gridHitbox; // grid hitbox 테이블

	// Button
	private ImageButton attackButton;
	private ImageButton skillButton;
	private ImageButton inventoryButton;
	private ImageButton defenseButton;
	private ImageButton waitButton;
	private ImageButton escapeButton;

	private Monster selectedMonster;

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
		if (gridHitbox.isGridShow()) {

		} else if (monsterTrigger) {
			Unit actor = getCurrentActor();
			if (!(actor instanceof Monster)) {
				// 일어날 수 없는 시나리오
				// 몬스터의 턴이 아니라면 monsterTrigger가 true여서는 안된다.
				return;
			}
			battleManager.monsterAttack();
			updateOrder();

			monsterTrigger = false;
		}

		super.act(delta);
	}

	public Stage makeStage() {
		super.makeStage();
		Gdx.app.debug("BattleStage", "makeStage(Rm rm)");

		selectedMonster = battleManager.getSelectedMonster();

		makeFirstOrder();

		// make table stack and add to stage
		tableStack.add(makeBattleUiTable());
		tableStack.add(makeGridHitbox());

		addListener();

		updateOrder();

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

	/**
	 * 전투 참여 유닛들의 순서를 결정
	 */
	private void makeFirstOrder() {
		units = new ArrayList<Unit>(4);
		units.addAll(partyManager.getPartyList());
		units.add(selectedMonster);

		// 행동게이지 초기화
		for (Unit unit : units) {
			unit.setGauge(100);
		}

		// 속도에 따라 정렬
		Collections.sort(units);

		orderedUnits = new LinkedList<Unit>(units);
	}

	public Table makeBattleUiTable() {
		Table uiTable = new Table();
		Table RMenuTable = makeRMenuTable();

		uiTable.right().bottom();
		uiTable.add(RMenuTable);

		return uiTable;
	}

	private Table makeRMenuTable() {
		Table RMenuTable = new Table();
		makeRButton();

		RMenuTable.add(attackButton).width(uiConstantsMap.get("RButtonWidth"))
				.height(uiConstantsMap.get("RButtonHeight"))
				.padTop(uiConstantsMap.get("RMenuTablePadTop"))
				.padBottom(uiConstantsMap.get("RButtonSpace")).expandX();
		RMenuTable.row();
		RMenuTable.add(skillButton).width(uiConstantsMap.get("RButtonWidth"))
				.height(uiConstantsMap.get("RButtonHeight"))
				.padBottom(uiConstantsMap.get("RButtonSpace"));
		RMenuTable.row();
		RMenuTable.add(inventoryButton)
				.width(uiConstantsMap.get("RButtonWidth"))
				.height(uiConstantsMap.get("RButtonHeight"))
				.padBottom(uiConstantsMap.get("RButtonSpace"));
		RMenuTable.row();
		RMenuTable.add(defenseButton).width(uiConstantsMap.get("RButtonWidth"))
				.height(uiConstantsMap.get("RButtonHeight"))
				.padBottom(uiConstantsMap.get("RButtonSpace"));
		RMenuTable.row();
		RMenuTable.add(waitButton).width(uiConstantsMap.get("RButtonWidth"))
				.height(uiConstantsMap.get("RButtonHeight"))
				.padBottom(uiConstantsMap.get("RButtonSpace"));
		RMenuTable.row();
		RMenuTable.add(escapeButton).width(uiConstantsMap.get("RButtonWidth"))
				.height(uiConstantsMap.get("RButtonHeight"));

		return RMenuTable;
	}

	private Table makeGridHitbox() {
		gridHitbox = new GridHitbox(MonsterEnum.SizeType.MEDIUM);
		return gridHitbox;

	}

	private void updateOrder() {
		Collections.sort(units);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean result = super.touchDown(screenX, screenY, pointer, button);

		if (gridHitbox.isGridShow()
				&& gridHitbox.isInsideHitbox(touched.x, touched.y)) {
			gridHitbox.showTileWhereClicked(screenX, screenY);
		}
		return result;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		boolean result = super.touchDragged(screenX, screenY, pointer);

		Gdx.app.log("deb", touched.toString());
		if (gridHitbox.isGridShow()) {
			gridHitbox.showTileWhereMoved(touched.x, touched.y);
		}
		return result;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		boolean result = super.touchUp(screenX, screenY, pointer, button);

		if (gridHitbox.isGridShow()
				&& gridHitbox.isInsideHitbox(touched.x, touched.y)) {
			Gdx.app.log("BattleStage", "어택!");

			Unit actor = getCurrentActor();

			if (!(actor instanceof Hero)) {
				// 일어날 수 없는 시나리오
				// 만약 몬스터의 턴이라면 이 이벤트가 호출되지 않아야 한다.
			}

			battleManager.userAttack(actor);
			updateOrder();

			if (whoIsNextActor() instanceof Monster) {
				monsterTrigger = true;
			}

			gridHitbox.hideGrid();
			//FIXME : 리스너를 만들 수 경우의 분기 체크
			if (eventCheckManager.checkBattleEvent()) {
				for (StorySectionPacket nextStorySectionPacket : storySectionManager
						.getNextSections()) {
					if (eventCheckManager.checkBattleControlEvent(
							nextStorySectionPacket, NORMAL_ATTACK)) {
						storySectionManager
								.setNewStorySectionAndPlay(nextStorySectionPacket
										.getNextSectionNumber());
					}
					break;
				}
			}
		}

		gridHitbox.hideAllTiles();

		return result;
	}

	public void addListener() {

		// 클릭시 발동
		attackButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!gridHitbox.isGridShow()) {
					gridHitbox.showGrid();
				} else {

				}
			}
		});

		skillButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "스킬!");
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
				battleManager.runAway();
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
