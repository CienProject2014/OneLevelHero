package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.Unit;
import com.mygdx.table.GridHitbox;

public class BattleStage extends BaseOneLevelStage {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AtlasUiAssets atlasUiAssets;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("BattleStage");
	// Table
	private Table orderTable; // 순서를 나타내는 테이블
	private GridHitbox gridHitbox; // grid hitbox 테이블
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private AnimationManager animationManager;

	// Button
	private ImageButton attackButton;
	private ImageButton skillButton;
	private ImageButton inventoryButton;
	private ImageButton defenseButton;
	private ImageButton waitButton;
	private ImageButton escapeButton;
	private ArrayList<ImageButton> imageButtonList;
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
		super.act(delta);
		if (gridHitbox.isGridShow()) {

		} else if (monsterTrigger) {
			Unit actor = getCurrentActor();
			Hero randomHero = partyManager.pickRandomHero();
			battleManager.playMonsterHitAnimation();
			battleManager.monsterAttack(randomHero);
			battleManager.checkMonsterWin(randomHero);

			updateOrder();
			monsterTrigger = false;
		}

		if (animationManager.hasPlayable()) {
			animationManager.nextFrame(delta);
			if (animationManager.getAnimations().isEmpty()) {
				storySectionManager.triggerSectionEvent(
						EventTypeEnum.BATTLE_CONTROL, "normal_attack");
			}
		} else {

		}
	}

	public Stage makeStage() {
		super.makeStage();
		Gdx.app.debug("BattleStage", "makeStage(Rm rm)");

		selectedMonster = battleManager.getSelectedMonster();

		makeFirstOrder();

		// make table stack and add to stage
		tableStack.add(makeBattleUiTable());
		gridHitbox = new GridHitbox();
		gridHitbox.setSizeType(MonsterEnum.SizeType.MEDIUM);
		tableStack.add(gridHitbox);

		addListener();

		updateOrder();

		return this;
	}

	private Unit getCurrentActor() {
		Unit unit = orderedUnits.poll();
		orderedUnits.add(unit);
		if (unit instanceof Hero) {
			battleManager.setCurrentActor((Hero) unit);
		}
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
		uiTable.padRight(uiConstantsMap.get("RMenuTablePadRight")).padBottom(
				uiConstantsMap.get("RMenuTablePadBottom"));
		uiTable.add(RMenuTable);

		return uiTable;
	}

	private Table makeRMenuTable() {
		Table rMenuTable = new Table();
		makeRButton();

		imageButtonList = new ArrayList<>();
		imageButtonList.add(attackButton);
		imageButtonList.add(skillButton);
		imageButtonList.add(inventoryButton);
		imageButtonList.add(defenseButton);
		imageButtonList.add(waitButton);
		imageButtonList.add(escapeButton);

		for (int i = 0; i < imageButtonList.size(); i++) {
			if (i == 0) {
				rMenuTable.add(imageButtonList.get(i))
						.width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight"))
						.padTop(uiConstantsMap.get("RMenuTablePadTop"))
						.padBottom(uiConstantsMap.get("RButtonSpace"))
						.expandX();
				rMenuTable.row();
			} else {
				rMenuTable.add(imageButtonList.get(i))
						.width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight"))
						.padBottom(uiConstantsMap.get("RButtonSpace"));
				rMenuTable.row();
			}
		}
		/*
		 * 다른버튼 막기 //FIXME if (eventCheckManager.checkBattleEventType()) {
		 * switch (eventCheckManager.getBattleControlButton()) { case
		 * NORMAL_ATTACK: imageButtonList.remove(attackButton); setDarkButton();
		 * break; case SKILL_ATTACK: imageButtonList.remove(skillButton);
		 * setDarkButton(); break; default: Gdx.app.log("BattleStage",
		 * "Rmenu ImageButton Target 에러"); break; } }
		 */

		return rMenuTable;
	}

	private void setDarkButton() {
		for (int i = 0; i < imageButtonList.size(); i++) {
			ImageButton imageButton = imageButtonList.get(i);
			imageButton.setColor(Color.DARK_GRAY);
			imageButton.setTouchable(Touchable.disabled);
		}
	}

	private void updateOrder() {
		Collections.sort(units);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean result = super.touchDown(screenX, screenY, pointer, button);

		if (gridHitbox.isGridShow()
				&& gridHitbox.isInsideHitbox(touched.x, touched.y)) {
			gridHitbox.showTileWhereClicked(touched.x, touched.y);
		}
		return result;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		boolean result = super.touchDragged(screenX, screenY, pointer);

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
				Gdx.app.log("BattleStage", "마왕의 턴");
			}

			battleManager.userAttack(actor);
			battleManager.playPlayerHitAnimation();
			battleManager.checkUserWin();
			updateOrder();

			gridHitbox.hideGrid();
		}
		gridHitbox.hideAllTiles();

		if (whoIsNextActor() instanceof Monster) {
			monsterTrigger = true;

		}

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
				gridHitbox.hideGrid();
				screenFactory.show(ScreenEnum.SKILL);
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
		attackButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("battleui_rb_attack"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_attack"));
		skillButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("battleui_rb_skill"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_skill"));
		inventoryButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("battleui_rb_item"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_item"));
		defenseButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("battleui_rb_defense"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_defense"));
		waitButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("battleui_rb_wait"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_wait"));
		escapeButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("battleui_rb_escape"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_escape"));
		addListener();
	}
}
