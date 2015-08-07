package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.BattleStateEnum;
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

	// RMenuButton
	private ImageButton attackButton, skillButton, inventoryButton,
			defenseButton, waitButton, escapeButton;
	private ArrayList<ImageButton> rMenuButtonList;
	private Monster selectedMonster;

	// Unit array
	private ArrayList<Unit> units;
	private Queue<Unit> orderedUnits;

	private boolean monsterTurn;
	private float animationDelay;
	private final float MONSTER_ATTACK_DELAY = 1.5f;

	@Override
	public void act(float delta) {
		super.act(delta);
		if (isMonsterTurn()) {
			doMonsterTurn(delta);
		}
		if (animationManager.isPlayable()) {
			playAnimation(delta);
		}
	}

	public Stage makeStage() {
		super.makeStage();
		selectedMonster = battleManager.getSelectedMonster();
		units = new ArrayList<Unit>(4);
		units.addAll(partyManager.getPartyList());
		units.add(selectedMonster);
		if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)) {
			initializeBattle(units, selectedMonster);
		}
		updateOrder();
		orderedUnits = new LinkedList<Unit>(units);

		tableStack.add(makeBattleUiTable());
		gridHitbox = new GridHitbox(); // 평소에는 hidden
		gridHitbox.setSizeType(MonsterEnum.SizeType.MEDIUM);
		tableStack.add(gridHitbox);

		addListener();

		return this;
	}

	private void initializeBattle(ArrayList<Unit> units,
			Monster selectedMonster) {
		battleManager.setBattleState(BattleStateEnum.IN_GAME);
		for (Unit unit : units) {
			unit.setGauge(100);
		}
		selectedMonster.setGauge(100);
		selectedMonster.getStatus()
				.setHp(selectedMonster.getStatus().getMaxHp());
	}

	private Unit getCurrentActor() {
		Unit currentAttackUnit = orderedUnits.poll();
		orderedUnits.add(currentAttackUnit);
		if (currentAttackUnit instanceof Hero) {
			battleManager.setCurrentActor((Hero) currentAttackUnit);
		}
		return currentAttackUnit;
	}

	private Unit whoIsNextActor() {
		Unit unit = orderedUnits.peek();
		return unit;
	}

	private void updateOrder() {
		Collections.sort(units);
	}

	private void doMonsterTurn(float delta) {
		Hero randomHero = partyManager.pickRandomHero();
		animationDelay += delta;
		hideRMenuButtons();
		if (animationDelay > MONSTER_ATTACK_DELAY) {
			Unit currentMonster = getCurrentActor();
			battleManager.attack(currentMonster, randomHero);
			updateOrder();
			setMonsterTurn(false);
			showRMenuButtons();
			animationDelay = 0;
		}
	}

	private void hideRMenuButtons() {
		for (ImageButton rMenuButton : rMenuButtonList) {
			rMenuButton.setVisible(false);
		}
	}

	private void showRMenuButtons() {
		for (ImageButton rMenuButton : rMenuButtonList) {
			rMenuButton.setVisible(true);
		}
	}

	private void playAnimation(float delta) {
		animationManager.nextFrame(delta);
		if (animationManager.getAnimations().isEmpty()) {
			// FIXME
			storySectionManager.triggerSectionEvent(
					EventTypeEnum.BATTLE_CONTROL, "normal_attack");
			if (battleManager.getBattleState()
					.equals(BattleStateEnum.GAME_OVER)) {
				battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				battleManager.goCurrentPosition();
			}
		}
	}

	public Table makeBattleUiTable() {
		Table uiTable = new Table();
		Table RMenuTable = makeRMenuTable();

		uiTable.right().bottom();
		uiTable.padRight(uiConstantsMap.get("RMenuTablePadRight"))
				.padBottom(uiConstantsMap.get("RMenuTablePadBottom"));
		uiTable.add(RMenuTable);

		return uiTable;
	}

	private Table makeRMenuTable() {
		Table rMenuTable = new Table();
		makeRButton();

		rMenuButtonList = new ArrayList<>();
		rMenuButtonList.add(attackButton);
		rMenuButtonList.add(skillButton);
		rMenuButtonList.add(inventoryButton);
		rMenuButtonList.add(defenseButton);
		rMenuButtonList.add(waitButton);
		rMenuButtonList.add(escapeButton);

		for (int i = 0; i < rMenuButtonList.size(); i++) {
			if (i == 0) {
				rMenuTable.add(rMenuButtonList.get(i))
						.width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight"))
						.padTop(uiConstantsMap.get("RMenuTablePadTop"))
						.padBottom(uiConstantsMap.get("RButtonSpace"))
						.expandX();
				rMenuTable.row();
			} else {
				rMenuTable.add(rMenuButtonList.get(i))
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
		for (int i = 0; i < rMenuButtonList.size(); i++) {
			ImageButton imageButton = rMenuButtonList.get(i);
			imageButton.setColor(Color.DARK_GRAY);
			imageButton.setTouchable(Touchable.disabled);
		}
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

		gridHitbox.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (gridHitbox.isGridShow()
						&& gridHitbox.isInsideHitbox(touched.x, touched.y)) {
					gridHitbox.setStartPosition(touched.x, touched.y);
					gridHitbox.showTileWhereClicked(touched.x, touched.y);
				}
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				if (gridHitbox.isGridShow()) {
					gridHitbox.showTileWhereClicked(touched.x, touched.y);
				}
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer,
					int button) {
				gridHitbox.hideAllTiles();
				if (whoIsNextActor() instanceof Monster) {
					setMonsterTurn(true);
				}
				if (gridHitbox.isGridShow()
						&& gridHitbox.isInsideHitbox(touched.x, touched.y)) {
					Unit currentHero = getCurrentActor();
					battleManager.attack(currentHero, selectedMonster);
					gridHitbox.hideGrid();
				}

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
	}

	public boolean isMonsterTurn() {
		return monsterTurn;
	}

	public void setMonsterTurn(boolean monsterTurn) {
		this.monsterTurn = monsterTurn;
	}

}
