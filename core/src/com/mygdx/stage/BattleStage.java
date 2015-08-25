package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.item.Weapon;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;
import com.mygdx.screen.BattleScreen;

public class BattleStage extends BaseOneLevelStage {
	private final String TAG = "BattleStage";
	private final int NORMAL_ATTACK = 30;
	private final int DEFENSE = 20;
	private final int RUN = 30;

	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	// Table
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private AnimationManager animationManager;

	// RMenuButton
	private ImageButton attackButton, skillButton, inventoryButton, defenseButton, waitButton, escapeButton;
	private ArrayList<ImageButton> rMenuButtonList;
	private Monster selectedMonster;

	private Queue<Unit> orderedUnits;

	private float animationDelay;
	private final float MONSTER_ATTACK_DELAY = 1.5f;
	// Image
	private Image currentAttackerBackground;
	private Image turnTableBackground;
	private HashMap<String, Image> turnBigImageMap = new HashMap<String, Image>();
	private HashMap<String, Image> turnSmallImageMap = new HashMap<String, Image>();
	// Table
	private Table imageTable = new Table();
	private Table bigImageTable = new Table();
	private Table smallImageTable = new Table();
	private Table turnTable = new Table();
	private Table rMenuTable = new Table();
	private int check = 0;
	private Vector2 start, end;
	private Unit currentAttackUnit;

	@Override
	public void act(float delta) {
		super.act(delta);

		if (battleManager.getCurrentAttackUnit() instanceof Monster) {
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

		if (battleManager.isShowGrid()) {
			battleManager.setShowGrid(true);
		} else {
			battleManager.setShowGrid(false);
		}
		if (battleManager.isSmallUpdate()) {
			updateSmallImageTable();
			battleManager.setSmallUpdate(false);
		}
		if (battleManager.isBigUpdate()) {
			updateBigImageTable();
			battleManager.setBigUpdate(false);
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		if (battleManager.isShowGrid() && battleManager.getGridHitbox().isInsideHitbox(touched.x, touched.y)) {
			start = (new Vector2(touched.x, touched.y));
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		super.touchDragged(screenX, screenY, pointer);
		if (battleManager.isShowGrid()) {
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
		uiConstantsMap = constantsAssets.getUiConstants("BattleStage");
		battleManager.setShowGrid(false);
		selectedMonster = battleManager.getSelectedMonster();
		ArrayList<Unit> units = new ArrayList<Unit>(4);
		units.addAll(partyManager.getBattleMemberList());
		units.add(selectedMonster);
		battleManager.setUnits(units);
		if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)) {
			initializeBattle(battleManager.getUnits(), selectedMonster);
			showMenuBarAnimation();
		}
		battleManager.gameObjectPopup = new SkillRunPopup();
		battleManager.setSkill(false);
		battleManager.updateOrder();
		currentAttackUnit = battleManager.getCurrentActors(); // 여기선 첫번째 턴
		battleManager.setCurrentAttackUnit(currentAttackUnit);
		tableStack.add(makeBattleUiTable()); // Rmenu
		tableStack.add(makeTurnTable()); // TurnTable 배경 테이블
		tableStack.add(makeTurnFaceTable()); // TurnTable위에 있는 영웅들 이미지 테이블
		battleManager.setMonsterSize(MonsterEnum.SizeType.MEDIUM);
		tableStack.add(battleManager.getGridHitbox());

		addListener();
		return this;
	}

	private void initializeBattle(ArrayList<Unit> units, Monster selectedMonster) {
		for (Unit unit : units) {
			unit.setGauge(100);
			unit.setSubvalue(0);
			unit.setActingPower(-24000 / (unit.getStatus().getSpeed() + 300) + 160);
		}
		selectedMonster.getStatus().setHp(selectedMonster.getStatus().getMaxHp());
		battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
		battleManager.setBattleState(BattleStateEnum.IN_GAME);
	}

	private void doMonsterTurn(float delta) {
		if (animationDelay == 0) {
			battleManager.hideRMenuButtons();
		}
		animationDelay += delta;
		if (animationDelay > MONSTER_ATTACK_DELAY) {
			Hero randomHero = partyManager.pickRandomHero();
			battleManager.calCostGague(battleManager.getCurrentAttackUnit(), NORMAL_ATTACK);
			battleManager.attack(battleManager.getCurrentAttackUnit(), randomHero, null);
			battleManager.endTurn();
			animationDelay = 0;
		}
	}

	private void playAnimation(float delta) {
		animationManager.nextFrame(delta);
		if (animationManager.getAnimations().isEmpty()) {
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_CONTROL, "normal_attack");
			battleManager.endTurn();
			if (battleManager.getBattleState().equals(BattleStateEnum.PLAYER_WIN)) {
				battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				movingManager.goPreviousPosition();
				storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_END, selectedMonster.getFacePath());
			} else if (battleManager.getBattleState().equals(BattleStateEnum.GAME_OVER)) {
				screenFactory.show(ScreenEnum.GAME_OVER);
			}
		}
	}

	private Table makeBattleUiTable() {
		Table uiTable = new Table();
		rMenuTable = makeRMenuTable();
		uiTable.right().bottom();
		uiTable.add(rMenuTable);
		return uiTable;
	}

	private void showMenuBarAnimation() {
		// 일단 밖으로 빼고 다시 원래대로~ (왼쪽에서 오른쪽으로)
		rMenuTable.addAction(Actions.moveTo(1920, 15));
		rMenuTable.addAction(Actions.moveTo(1720, 15, 1));

		turnTable.addAction(Actions.moveTo(15, -137));
		turnTable.addAction(Actions.moveTo(15, 15, 1));

		imageTable.addAction(Actions.moveTo(15, -137));
		imageTable.addAction(Actions.moveTo(15, 20, 1));

	}

	private Table makeTurnTable() {
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
		turnBigImageMap.get(battleManager.getCurrentAttackUnit().getFacePath()).setWidth(108);
		turnBigImageMap.get(battleManager.getCurrentAttackUnit().getFacePath()).setHeight(108);
		bigImageTable.add(turnBigImageMap.get(battleManager.getCurrentAttackUnit().getFacePath())).padRight(5);
		return bigImageTable;
	}

	private Table makeSmallImageTable() {
		orderedUnits = battleManager.getOrderedUnits();
		for (Unit unit : orderedUnits) {
			turnSmallImageMap.get(unit.getFacePath()).setWidth(80);
			turnSmallImageMap.get(unit.getFacePath()).setHeight(80);
			smallImageTable.add(turnSmallImageMap.get(unit.getFacePath())).padLeft(12).padBottom(0);
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
		battleManager.setrMenuButtonList(rMenuButtonList);
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

	private void setDarkButton(ImageButton button) {
		for (ImageButton buttons : rMenuButtonList) {
			buttons.setVisible(true);
			buttons.setTouchable(Touchable.enabled);
		}
		button.setVisible(false);
		button.setTouchable(Touchable.disabled);
	}

	private int getWeaponHitboxSize() {
		Hero forInv = (Hero) battleManager.getCurrentAttackUnit();
		Weapon weapon = (Weapon) forInv.getInventory().getEquipment(ItemEnum.LEFT_HANDGRIP);
		return weapon.getHitboxSize();
	}

	private void addListener() {
		// 클릭시 발동
		attackButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				battleManager.checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.NORMAL);
				setDarkButton(attackButton);
				battleManager.afterClick(NORMAL_ATTACK);

				battleManager.setGridLimitNum(getWeaponHitboxSize());
				battleManager.setShowGrid(true);
			}
		});

		skillButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				battleManager.checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.SKILL);
				setDarkButton(skillButton);
				battleManager.setSkill(true);
				BattleScreen.showSkillStage = true;
			}
		});

		inventoryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				battleManager.checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.INVENTORY);
				setDarkButton(inventoryButton);
				Gdx.app.log(TAG, "인벤토리!");
			}
		});

		defenseButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				battleManager.checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.DEFENSE);
				setDarkButton(defenseButton);
				battleManager.afterClick(DEFENSE);
				battleManager.endTurn();
				Gdx.app.log(TAG, "방어!");

			}
		});

		waitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				battleManager.checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.WAIT);
				setDarkButton(waitButton);
				battleManager.waitButton();
				battleManager.endTurn();
				Gdx.app.log(TAG, "기다립시다!");

			}
		});

		escapeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				battleManager.checkCurrentState();
				battleManager.setCurrentClickStateEnum(CurrentClickStateEnum.RUN);
				setDarkButton(escapeButton);
				battleManager.afterClick(RUN);
				battleManager.gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
				battleManager.gameObjectPopup.setListenerFactory(listenerFactory);
				battleManager.gameObjectPopup.setConstantsAssets(constantsAssets);
				checkRunAway();
				battleManager.gameObjectPopup
						.initialize("도망 치시겠습니까?" + "\n" + "도망칠 확률" + battleManager.getRunPercent() + "%입니다");
				addActor(battleManager.gameObjectPopup);
				battleManager.gameObjectPopup.setVisible(true);
			}
		});

		battleManager.getGridHitbox().addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (battleManager.isShowGrid() && battleManager.getGridHitbox().isInsideHitbox(touched.x, touched.y)) {
					battleManager.getGridHitbox().setStartPosition(touched.x, touched.y);
					battleManager.getGridHitbox().showTileWhereMoved(touched.x, touched.y);
				}
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if (battleManager.isShowGrid()) {
					if (!battleManager.isSkill()) {
						battleManager.getGridHitbox().showTileWhereMoved(touched.x, touched.y);
					} else {
						if (battleManager.getGridHitbox().getHitboxCenter() == null) {
							Gdx.app.log(TAG, "skill limit num: " + battleManager.getGridLimitNum());
							battleManager.getGridHitbox().showTileWhereMoved(touched.x, touched.y);
						} else {
							battleManager.getGridHitbox().showFixedTilesAt(touched.x, touched.y);
						}

					}
				}
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (battleManager.isShowGrid() && battleManager.getGridHitbox().isInsideHitbox(touched.x, touched.y)) {
					if (!battleManager.isSkill()) {
						battleManager.attack(battleManager.getCurrentAttackUnit(), selectedMonster,
								battleManager.getGridHitbox().getPreviousHitArea());
					} else {
						battleManager.useSkill(battleManager.getCurrentAttackUnit(), selectedMonster,
								battleManager.getCurrentSelectedSkill().getSkillPath());
						battleManager.setSkill(false);
					}
					battleManager.setShowGrid(false);
				}
				battleManager.setShowGrid(false);
				battleManager.getGridHitbox().hideAllTiles();
			}
		});
	}

	private void checkRunAway() {
		int minSpeed = 500;
		for (Unit unit : partyManager.getBattleMemberList()) {
			if (minSpeed >= unit.getStatus().getSpeed()) {
				minSpeed = unit.getStatus().getSpeed();
			}
		}
		check = 50 + battleManager.getSelectedMonster().getStatus().getSpeed() - minSpeed;
		battleManager.setRunPercent(check);
	}

	private void makeTurnBackgroundImage() {
		currentAttackerBackground = new Image(textureManager.getEtcTexture("battleui_turntable_01"));
		turnTableBackground = new Image(textureManager.getEtcTexture("battleui_turntable_02"));
	}

	private void makeBattleTurnImage() {
		turnBigImageMap.put(selectedMonster.getFacePath(),
				new Image(textureManager.getBigBattleImage(selectedMonster.getFacePath())));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnBigImageMap.put(hero.getFacePath(), new Image(textureManager.getBigBattleImage(hero.getFacePath())));
		}
		turnSmallImageMap.put(selectedMonster.getFacePath(),
				new Image(textureManager.getSmallBattleImage(selectedMonster.getFacePath())));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnSmallImageMap.put(hero.getFacePath(),
					new Image(textureManager.getSmallBattleImage(hero.getFacePath())));
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
