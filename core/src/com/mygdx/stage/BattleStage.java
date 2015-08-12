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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.ui.GridHitbox;

public class BattleStage extends BaseOneLevelStage {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AtlasUiAssets atlasUiAssets;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("BattleStage");
	// Table
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
	private Unit currentHero;

	private boolean monsterTurn;
	private float animationDelay;
	private final float MONSTER_ATTACK_DELAY = 1.5f;

	// Image
	private Image currentAttackerBackground;
	private Image turnTableBackground;
	private HashMap<String, Image> turnBigImageMap = new HashMap<String, Image>();
	private HashMap<String, Image> turnSmallImageMap = new HashMap<String, Image>();
	private Table imageTable = new Table();

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
		units.addAll(partyManager.getBattleMemberList());
		units.add(selectedMonster);
		if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)) {
			initializeBattle(units, selectedMonster);
		}
		updateOrder();
		orderedUnits = new LinkedList<Unit>(units);
		currentHero = whoIsNextActor(); // 여기선 첫번째 턴
		tableStack.add(makeBattleUiTable());
		tableStack.add(makeTurnTable());
		tableStack.add(makeTurnFaceImageTable());
		gridHitbox = new GridHitbox(); // 평소에는 hidden
		gridHitbox.setSizeType(MonsterEnum.SizeType.MEDIUM);
		tableStack.add(gridHitbox);

		addListener();
		return this;
	}

	private void initializeBattle(ArrayList<Unit> units, Monster selectedMonster) {
		battleManager.setBattleState(BattleStateEnum.IN_GAME);
		for (Unit unit : units) {
			unit.setGauge(100);
		}
		selectedMonster.setGauge(100);
		selectedMonster.getStatus().setHp(
				selectedMonster.getStatus().getMaxHp());
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
			Unit currentMonster = currentHero;
			battleManager.attack(currentMonster, randomHero);
			setMonsterTurn(false);
			showRMenuButtons();
			animationDelay = 0;
		}
		updateImageTable();
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
			currentHero = getCurrentActor();
			updateImageTable();
			if (battleManager.getBattleState()
					.equals(BattleStateEnum.GAME_OVER)) {
				battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				movingManager.goPreviousPosition();
			}
		}

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

	private Table makeTurnTable() {
		Table turnTable = new Table();
		makeTurnBackgroundImage();
		makeBattleTurnImage();
		currentAttackerBackground.setWidth(137);
		currentAttackerBackground.setHeight(137);
		turnTable.add(currentAttackerBackground);
		turnTable.add(turnTableBackground);
		turnTable.left().bottom();
		turnTable.padLeft(15).padBottom(15);
		return turnTable;
	}

	private Table makeTurnFaceImageTable() {
		turnBigImageMap.get(currentHero.getFacePath()).setWidth(117);
		turnBigImageMap.get(currentHero.getFacePath()).setHeight(117);
		imageTable.add(turnBigImageMap.get(currentHero.getFacePath()))
				.padRight(15);

		for (Unit unit : orderedUnits) {
			turnSmallImageMap.get(unit.getFacePath()).setWidth(84);
			turnSmallImageMap.get(unit.getFacePath()).setHeight(84);
			imageTable.add(turnSmallImageMap.get(unit.getFacePath()));
		}
		imageTable.left().bottom();
		imageTable.padLeft(17).padBottom(25);

		return imageTable;
	}

	private void updateImageTable() {
		imageTable.reset();
		imageTable = makeTurnFaceImageTable();
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
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (gridHitbox.isGridShow()
						&& gridHitbox.isInsideHitbox(touched.x, touched.y)) {
					battleManager.attack(currentHero, selectedMonster);
					gridHitbox.hideGrid();
				}

				gridHitbox.hideAllTiles();
				if (whoIsNextActor() instanceof Monster) {
					setMonsterTurn(true);
				}

			}
		});
	}

	private void makeTurnBackgroundImage() {
		currentAttackerBackground = new Image(
				StaticAssets.battleUiTextureMap.get("battleui_turntable_01"));
		turnTableBackground = new Image(
				StaticAssets.battleUiTextureMap.get("battleui_turntable_02"));
	}

	private void makeBattleTurnImage() {
		turnBigImageMap.put(selectedMonster.getFacePath(), new Image(
				selectedMonster.getBigBattleTexture()));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnBigImageMap.put(hero.getFacePath(),
					new Image(hero.getBigBattleTexture()));
		}
		turnSmallImageMap.put(selectedMonster.getFacePath(), new Image(
				selectedMonster.getSmallBattleTexture()));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnSmallImageMap.put(hero.getFacePath(),
					new Image(hero.getSmallBattleTexture()));
		}
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
