package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
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
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.item.Weapon;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.screen.BattleScreen;
import com.mygdx.ui.GridHitbox;

public class BattleStage extends BaseOneLevelStage {
	private final String TAG = "BattleStage";

	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AtlasUiAssets atlasUiAssets;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("BattleStage");
	// Table
	private GridHitbox gridHitbox; // grid hitbox 테이블
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private AnimationManager animationManager;

	// RMenuButton
	private ImageButton attackButton, skillButton, inventoryButton, defenseButton, waitButton, escapeButton;
	private ArrayList<ImageButton> rMenuButtonList;
	private Monster selectedMonster;

	// Unit array
	private ArrayList<Unit> units;
	private Queue<Unit> orderedUnits;
	private Unit currentAttackUnit;

	private float animationDelay;
	private final float MONSTER_ATTACK_DELAY = 1.5f;
	private final int NORMAL_ATTACK = 30;
	// Image
	private Image currentAttackerBackground;
	private Image turnTableBackground;
	private HashMap<String, Image> turnBigImageMap = new HashMap<String, Image>();
	private HashMap<String, Image> turnSmallImageMap = new HashMap<String, Image>();
	private Table imageTable = new Table();
	private Table bigImageTable = new Table();
	private Table smallImageTable = new Table();
	private Table rMenuTable = new Table();

	private boolean isSkill = false;
	private Vector2 start, end;

	@Override
	public void act(float delta) {
		super.act(delta);
		if (currentAttackUnit instanceof Monster) {
			doMonsterTurn(delta);
		}
		if (animationManager.isPlayable()) {
			playAnimation(delta);
		}

		if (start != null && end != null) {

			Gdx.gl.glLineWidth(6.0f);
			ShapeRenderer sr = new ShapeRenderer();

			sr.setProjectionMatrix(getCamera().combined);

			sr.begin(ShapeType.Line);
			sr.setColor(com.badlogic.gdx.graphics.Color.RED);
			sr.line(start, end);
			sr.end();
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		if (gridHitbox.isGridShow() && gridHitbox.isInsideHitbox(touched.x, touched.y)) {
			start = (new Vector2(touched.x, touched.y));
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		super.touchDragged(screenX, screenY, pointer);
		if (gridHitbox.isGridShow()) {
			end = (new Vector2(touched.x, touched.y));

		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		start = null;
		end = null;

		return false;
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
		currentAttackUnit = getCurrentActor(); // 여기선 첫번째 턴
		tableStack.add(makeBattleUiTable());
		tableStack.add(makeTurnTable());
		tableStack.add(makeTurnFaceTable());
		gridHitbox = new GridHitbox(); // 평소에는 hidden
		gridHitbox.setSizeType(MonsterEnum.SizeType.MEDIUM);
		tableStack.add(gridHitbox);
		addListener();
		return this;
	}

	private Unit getCurrentActor() {
		Unit currentAttackUnit = orderedUnits.poll();
		if (currentAttackUnit instanceof Hero) {
			battleManager.setCurrentActor((Hero) currentAttackUnit);
		}
		return currentAttackUnit;
	}

	private void updateOrder() {
		// Turn Logic
		Collections.sort(units);
		orderedUnits = new LinkedList<Unit>(units);
	}

	private void initializeBattle(ArrayList<Unit> units, Monster selectedMonster) {
		battleManager.setBattleState(BattleStateEnum.IN_GAME);
		for (Unit unit : units) {
			unit.setGauge(100);
			unit.setSubvalue(0);
			unit.setActingPower(-24000 / (unit.getStatus().getSpeed() + 300) + 160);
		}
		selectedMonster.getStatus().setHp(selectedMonster.getStatus().getMaxHp());
		battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
	}

	private void doMonsterTurn(float delta) {
		if (animationDelay == 0) {
			hideRMenuButtons();
		}
		animationDelay += delta;
		if (animationDelay > MONSTER_ATTACK_DELAY) {
			Hero randomHero = partyManager.pickRandomHero();
			calCostGague(currentAttackUnit, NORMAL_ATTACK);// 랜덤으로 선택해야 한다
			battleManager.attack(currentAttackUnit, randomHero, null);
			endTurn();
			showRMenuButtons();
			animationDelay = 0;
		}
	}

	private void playAnimation(float delta) {
		animationManager.nextFrame(delta);
		if (animationManager.getAnimations().isEmpty()) {
			storySectionManager.triggerSectionEvent(EventTypeEnum.BATTLE_CONTROL, "normal_attack");
			endTurn();
			makeHiddenButton();
			if (battleManager.getBattleState().equals(BattleStateEnum.GAME_OVER)) {
				battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				movingManager.goPreviousPosition();
			}
		}
	}

	private void healGague() {
		int maxGague = 0;
		for (Unit unit : units) {
			if (maxGague <= unit.getGauge()) {
				maxGague = unit.getGauge();
			}
		}

		for (Unit unit : units) {
			unit.setGauge(unit.getGauge() + 100 - maxGague);
		}
	}

	private void endTurn() {
		updateOrder();
		currentAttackUnit = getCurrentActor();
		updateBigImageTable();
		updateSmallImageTable();
		battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
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

	public Table makeBattleUiTable() {
		Table uiTable = new Table();
		Table RMenuTable = makeRMenuTable();

		uiTable.right().bottom();
		uiTable.padRight(uiConstantsMap.get("RMenuTablePadRight")).padBottom(uiConstantsMap.get("RMenuTablePadBottom"));
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

	private Table makeTurnFaceTable() {
		imageTable.add(makeBigImageTable());
		imageTable.add(makeSmallImageTable());
		imageTable.left().bottom();
		imageTable.padLeft(17).padBottom(17);

		return imageTable;
	}

	private Table makeBigImageTable() {
		turnBigImageMap.get(currentAttackUnit.getFacePath()).setWidth(117);
		turnBigImageMap.get(currentAttackUnit.getFacePath()).setHeight(117);
		bigImageTable.add(turnBigImageMap.get(currentAttackUnit.getFacePath())).padRight(15);
		return bigImageTable;
	}

	private Table makeSmallImageTable() {
		for (Unit unit : orderedUnits) {
			turnSmallImageMap.get(unit.getFacePath()).setWidth(84);
			turnSmallImageMap.get(unit.getFacePath()).setHeight(84);
			smallImageTable.add(turnSmallImageMap.get(unit.getFacePath()));
		}
		return smallImageTable;
	}

	private void updateBigImageTable() {
		bigImageTable.reset();
		bigImageTable = makeBigImageTable();
	}

	private void updateSmallImageTable() {
		smallImageTable.reset();
		smallImageTable = makeSmallImageTable();
	}

	private Table makeRMenuTable() {
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
				rMenuTable.add(rMenuButtonList.get(i)).width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight")).padTop(uiConstantsMap.get("RMenuTablePadTop"))
						.padBottom(uiConstantsMap.get("RButtonSpace")).expandX();
				rMenuTable.row();
			} else {
				rMenuTable.add(rMenuButtonList.get(i)).width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight")).padBottom(uiConstantsMap.get("RButtonSpace"));
				rMenuTable.row();
			}
		}

		return rMenuTable;
	}

	private void makeHiddenButton() {
		switch (battleManager.getCurrentClickStateEnum()) {
		case NORMAL:
			calCostGague(currentAttackUnit, NORMAL_ATTACK);
			updateOrder();
			updateSmallImageTable();
			setDarkButton(attackButton);
			break;
		case SKILL:
			setDarkButton(skillButton);
			break;
		case INVENTORY:
			setDarkButton(inventoryButton);
			break;
		case DEFENSE:
			setDarkButton(defenseButton);
			break;
		case WAIT:
			setDarkButton(waitButton);
			break;
		case DEFAULT:
			setFreeButton();
			break;
		default:
			break;
		}
	}

	private void checkCurrentState() {
		switch (battleManager.getCurrentClickStateEnum()) {
		case NORMAL:
			gridHitbox.hideGrid();
			battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
			// currentHero.setGauge(preGague);
			break;
		case SKILL:
			battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
			// currentHero.setGauge(preGague);
			break;
		case INVENTORY:
			battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
			// currentHero.setGauge(preGague);
			break;
		case DEFENSE:
			battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
			// currentHero.setGauge(preGague);
			break;
		case WAIT:
			battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
			// currentHero.setGauge(preGague);
			break;
		case RUN:
			battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
			// currentHero.setGauge(preGague);
			break;
		default:
			break;
		}
	}

	private void setFreeButton() {
		for (ImageButton buttons : rMenuButtonList) {
			buttons.setVisible(true);
			buttons.setTouchable(Touchable.enabled);
		}
	}

	private void setDarkButton(ImageButton button) {
		for (ImageButton buttons : rMenuButtonList) {
			buttons.setVisible(true);
			buttons.setTouchable(Touchable.enabled);
		}
		button.setVisible(false);
		button.setTouchable(Touchable.disabled);
	}

	private void calCostGague(Unit unit, int typeOfAction) {
		unit.setPreGague(unit.getGauge());
		int costGague = (int) (((double) (150 - unit.getActingPower()) / 50) * typeOfAction);
		unit.setGauge(unit.getGauge() - costGague);
		healGague();
	}

	public void addListener() {
		// 클릭시 발동
		attackButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!gridHitbox.isGridShow()) {
					checkCurrentState();
					battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.NORMAL);
					makeHiddenButton();
					Hero forInv = (Hero) currentAttackUnit;
					Weapon w = (Weapon) forInv.getInventory().getEquipment(ItemEnum.LEFT_HANDGRIP);
					gridHitbox.setLimitNum(w.getHitboxSize());
					gridHitbox.showGrid();
				} else {

				}
			}
		});

		skillButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.SKILL);
				makeHiddenButton();

				// TODO: 유저가 선택한 스킬의 이름을 받아와야 함
				Skill s = currentAttackUnit.getSkills().get("cut_" + "01");

				if (s.getHitboxSize() == 0) { // grid가 고정된 형태인 경우
					gridHitbox.setHitboxCenter(s.getHitboxCenter());
					gridHitbox.setHitboxShape(s.getHitboxShape());
					gridHitbox.showGrid();
					Gdx.app.log(TAG, "gridHitbox를 표시합니다");
				} else {
					gridHitbox.setLimitNum(s.getHitboxSize());
					if (s.getHitboxCenter() == null) { // grid를 선택하는 경우
						gridHitbox.showGrid();
					} else { // grid 선택 없는 경우
						Gdx.app.log(TAG, "스킬 즉시 사용");
						battleManager.useSkill(currentAttackUnit, selectedMonster, "cut_01");
					}
				}
				isSkill = true;
				BattleScreen.showSkillStage = true;
			}
		});

		inventoryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.INVENTORY);
				makeHiddenButton();
				Gdx.app.log(TAG, "인벤토리!");
			}
		});
		defenseButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFENSE);
				makeHiddenButton();
				Gdx.app.log(TAG, "방어!");

			}
		});
		waitButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.WAIT);
				makeHiddenButton();
				Gdx.app.log(TAG, "기다립시다!");

			}
		});
		escapeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.RUN);
				Gdx.app.log(TAG, "도망!");
				battleManager.runAway();
			}
		});

		gridHitbox.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (gridHitbox.isGridShow() && gridHitbox.isInsideHitbox(touched.x, touched.y)) {
					gridHitbox.setStartPosition(touched.x, touched.y);
					gridHitbox.showTileWhereMoved(touched.x, touched.y);
				}
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {

				// TODO: 유저가 선택한 스킬의 이름을 받아와야 함
				Skill s = currentAttackUnit.getSkills().get("cut_" + "01");
				if (gridHitbox.isGridShow()) {
					if (!isSkill) {
						gridHitbox.showTileWhereMoved(touched.x, touched.y);
					} else {
						if (s.getHitboxCenter() == null) {
							gridHitbox.showTileWhereMoved(touched.x, touched.y);
						} else {
							gridHitbox.showFixedTilesAt(touched.x, touched.y);
						}

					}
				}
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (gridHitbox.isGridShow() && gridHitbox.isInsideHitbox(touched.x, touched.y)) {
					if (!isSkill) {
						battleManager.attack(currentAttackUnit, selectedMonster, gridHitbox.getPreviousHitArea());
					} else {
						battleManager.useSkill(currentAttackUnit, selectedMonster, "cut_01");
						isSkill = false;
					}
					gridHitbox.hideGrid();
				}
				gridHitbox.hideAllTiles();
			}
		});
	}

	private void makeTurnBackgroundImage() {
		currentAttackerBackground = new Image(
				StaticAssets.assetManager.get(StaticAssets.textureMap.get("battleui_turntable_01"), Texture.class));
		turnTableBackground = new Image(
				StaticAssets.assetManager.get(StaticAssets.textureMap.get("battleui_turntable_02"), Texture.class));
	}

	private void makeBattleTurnImage() {
		turnBigImageMap.put(selectedMonster.getFacePath(), new Image(selectedMonster.getBigBattleTexture()));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnBigImageMap.put(hero.getFacePath(), new Image(hero.getBigBattleTexture()));
		}
		turnSmallImageMap.put(selectedMonster.getFacePath(), new Image(selectedMonster.getSmallBattleTexture()));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnSmallImageMap.put(hero.getFacePath(), new Image(hero.getSmallBattleTexture()));
		}
	}

	private void makeRButton() {
		// 이미지 추가
		attackButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_attack"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_attack"));
		skillButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_skill"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_skill"));
		inventoryButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_item"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_item"));
		defenseButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_defense"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_defense"));
		waitButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_wait"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_wait"));
		escapeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_escape"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_escape"));
	}

}
