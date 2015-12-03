package com.mygdx.stage;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.BattleCommandEnum;
import com.mygdx.enums.BattleMessages;
import com.mygdx.enums.BattleSituationEnum;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.QuestManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.battle.BattleFlag;
import com.mygdx.model.battle.BattleInfo;
import com.mygdx.model.battle.BattleUi;
import com.mygdx.model.item.Weapon;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;

public class BattleStage extends BaseOneLevelStage {
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private transient QuestManager questManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private transient AtlasUiAssets atlasUiAssets;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private transient ConstantsAssets constantsAssets;
	@Autowired
	private transient UiComponentAssets uiComponentAssets;
	// Table
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private AnimationManager animationManager;
	@Autowired
	private SoundManager soundManager;
	private float animationDelay;
	private final float MONSTER_ATTACK_DELAY = 1.5f;
	// Image
	private Image currentAttackerBackground;
	private Image turnTableBackground;
	private TextureRegion textMenu;
	private BattleInfo battleState;
	private BattleFlag battleFlag;
	private BattleInfo battleInfo;
	private BattleUi battleUi;
	private HashMap<String, Image> turnBigImageMap = new HashMap<String, Image>();
	private HashMap<String, Image> turnSmallImageMap = new HashMap<String, Image>();
	// Table
	private Table imageTable = new Table();
	private Table currentActorImageTable = new Table();
	private Table wailtListImageTable = new Table();
	private Table turnTable = new Table();
	private Table textMenuTable = new Table();
	private Vector2 start, end;
	private Label battleDescriptionLabel;

	@Override
	public void act(float delta) {
		super.act(delta);

		if (battleManager.getCurrentActor() instanceof Monster) {
			doMonsterTurn(delta);
		}

		if (animationManager.isPlayable()) {
			playBattleAction(delta);
		} else {
			checkTurnEnd();
		}

		if (isLineTouchOccured()) {
			drawRedTouchLine();
		}

		if (battleManager.isSmallUpdate()) {
			updateSmallImageTable();
			battleManager.setSmallTurnTableUpdate(false);
		}

		if (battleManager.isBigTurnTableUpdate()) {
			updateBigImageTable();
			battleManager.setBigTurnTableUpdate(false);
		}
		updateBattleDescriptionLabel();
		cameraManager.act(delta);
	}

	private void checkTurnEnd() {
		battleManager.checkTurnEnd();
	}

	private void drawRedTouchLine() {
		Gdx.gl.glLineWidth(6.0f);
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(getCamera().combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
		shapeRenderer.line(start, end);
		shapeRenderer.end();
	}

	private boolean isLineTouchOccured() {
		if (this.start != null && this.end != null) {
			return true;
		}
		return false;
	}

	public Stage makeStage() {
		super.makeStage();
		battleFlag = battleManager.getBattleFlag();
		battleInfo = battleManager.getBattleInfo();
		battleUi = battleManager.getBattleUi();
		battleUi.setSkillRunPopup(new SkillRunPopup());
		if (battleState.getCurrentBattleSituation().equals(BattleSituationEnum.ENCOUNTER)) {
			showInEncounterCase(partyManager.getBattleMemberList(), battleState.getCurrentMonster());
		}

		tableStack.add(makeTurnFrameTable()); // TurnTable 배경 테이블
		tableStack.add(makeTurnFaceTable()); // TurnTable위에 있는 영웅들 이미지 테이블
		battleManager.setMonsterSize(MonsterEnum.SizeType.MEDIUM); // TODO 고치고,
																	// 옮기세요

		tableStack.add(battleManager.getGridHitbox());
		battleFlag.setShowGrid(false); // TODO 옮기세요
		battleUi.setBattleStage(this);

		return this;
	}

	private void showInEncounterCase(List<Hero> battleMemberList, Monster currentMonster) {
		soundManager.setSoundByPathAndPlay("notice_encount");
		battleManager.initializeBattle(battleMemberList, currentMonster);
		showMenuBarAnimation();
		showBattleInfoMessage(BattleMessages.START_BATTLE_MESSAGE);
	}

	private void showBattleInfoMessage(BattleMessages battleInfoMessage) {
		battleManager.showBattleInfoMessage(battleInfoMessage.toString());
	}

	private void doMonsterTurn(float delta) { // 스테이지가 굳이 할거 아니면 매니저로 빼자
		battleFlag.setMonsterTurnEnd(false);
		animationDelay += delta;
		if (animationDelay > MONSTER_ATTACK_DELAY) {
			Hero randomHero = partyManager.pickRandomHero();
			battleManager.applyGauge(BattleCommandEnum.GENERAL_ATTACK.getCostGauge());
			battleManager.doBattleCommand(battleManager.getCurrentActor(), randomHero, null);
			battleManager.handleTurnEnd();
			animationDelay = 0;
		}
	}

	/***
	 * playBattleAction 애니메이션을 재생한다.
	 * 
	 * @param delta
	 * @param width
	 *            애니메이션 그림의 폭
	 * @param height
	 *            애니메이션 그림의 높이
	 */
	private void playBattleAction(float delta) {
		battleManager.playBattleAction(delta);
	}

	private void showMenuBarAnimation() {
		turnTable.addAction(Actions.moveTo(5, -137));
		turnTable.addAction(Actions.moveTo(5, 5, 1));
		imageTable.addAction(Actions.moveTo(5, -137));
		imageTable.addAction(Actions.moveTo(5, 5, 1));
	}

	private Table makeTurnFrameTable() {
		battleDescriptionLabel = new Label("text", uiComponentAssets.getSkin());
		battleDescriptionLabel.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		battleDescriptionLabel.setWrap(true);
		battleDescriptionLabel.setAlignment(Align.left, Align.left);

		textMenu = new TextureRegion(textureManager.getTexture("battleui_textmenu"));
		textMenuTable.setBackground(new TextureRegionDrawable(textMenu));
		textMenuTable.add(battleDescriptionLabel).width(750).height(10);
		makeTurnBackgroundImage();
		makeBattleTurnImage();
		currentAttackerBackground.setWidth(137);
		currentAttackerBackground.setHeight(137);
		turnTable.add(currentAttackerBackground);
		turnTable.add(turnTableBackground);
		turnTable.add(textMenuTable);

		turnTable.left().bottom();
		turnTable.padLeft(15).padBottom(15);
		return turnTable;
	}

	private void updateBattleDescriptionLabel() {
		if (battleInfo.getBattleDescriptionLabel() != null) {
			battleDescriptionLabel.setText(battleInfo.getBattleDescriptionLabel());
		} else {
			battleDescriptionLabel.setText("상황을 기다리는 중 입니다");
		}
	}

	private Table makeTurnFaceTable() {
		imageTable.add(makeBigTurnImageTable());
		imageTable.add(makeSmallTurnImageTable());
		imageTable.left().bottom();
		imageTable.padLeft(17).padBottom(17);
		return imageTable;
	}

	private Table makeBigTurnImageTable() {
		turnBigImageMap.get(battleInfo.getCurrentActor().getFacePath()).setWidth(108);
		turnBigImageMap.get(battleInfo.getCurrentActor().getFacePath()).setHeight(108);
		currentActorImageTable.add(turnBigImageMap.get(battleInfo.getCurrentActor().getFacePath())).padRight(5);
		return currentActorImageTable;
	}

	private Table makeSmallTurnImageTable() {
		PriorityQueue<Unit> orderedUnits = battleInfo.getBattleQueue();
		for (Unit unit : orderedUnits) {
			turnSmallImageMap.get(unit.getFacePath()).setWidth(80);
			turnSmallImageMap.get(unit.getFacePath()).setHeight(80);
			wailtListImageTable.add(turnSmallImageMap.get(unit.getFacePath())).padLeft(12).padBottom(0);
		}
		return wailtListImageTable;
	}

	private void updateBigImageTable() {
		currentActorImageTable.reset();
		currentActorImageTable = makeBigTurnImageTable();
	}

	private void updateSmallImageTable() {
		wailtListImageTable.reset();
		wailtListImageTable = makeSmallTurnImageTable();
	}

	private int getWeaponHitboxSize() {
		Hero forInv = (Hero) battleInfo.getCurrentActor();
		Weapon rightWeapon = (Weapon) forInv.getInventory().getEquipment(ItemEnum.RIGHT_HANDGRIP);
		Weapon leftWeapon = (Weapon) forInv.getInventory().getEquipment(ItemEnum.LEFT_HANDGRIP);
		if (rightWeapon.getHitboxSize() >= leftWeapon.getHitboxSize()) {
			return rightWeapon.getHitboxSize();
		} else {
			return leftWeapon.getHitboxSize();
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		if (battleFlag.isShowGrid()) {
			end = (new Vector2(touched.x, touched.y));
			if (battleUi.getGridHitbox().isInsideHitbox(touched.x, touched.y)) {
				if (battleFlag.isUsingSkill()) {
					battleManager.setGridLimitNum(battleInfo.getCurrentSelectedSkill().getHitboxSize());
				} else {
					battleManager.setGridLimitNum(getWeaponHitboxSize());
				}
				start = (new Vector2(touched.x, touched.y));
				battleUi.getGridHitbox().setStartPosition(touched.x, touched.y);
				battleUi.getGridHitbox().showTileWhereMoved(touched.x, touched.y);
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		super.touchDragged(screenX, screenY, pointer);
		if (battleFlag.isShowGrid()) {
			end = (new Vector2(touched.x, touched.y));
			if (!battleFlag.isUsingSkill()) {
				battleUi.getGridHitbox().showTileWhereMoved(touched.x, touched.y);
			} else {
				if (battleUi.getGridHitbox().getHitboxCenter() == null) {
					battleUi.getGridHitbox().showTileWhereMoved(touched.x, touched.y);
				} else {
					battleUi.getGridHitbox().showFixedTilesAt(touched.x, touched.y);
				}
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		if (battleFlag.isShowGrid() && battleUi.getGridHitbox().isInsideHitbox(touched.x, touched.y)) {
			if (!battleFlag.isUsingSkill()) {
				soundManager.setSoundByPathAndPlay("slash");
				battleManager.doBattleCommand(battleManager.getCurrentActor(), battleManager.getCurrentMonster(),
						battleManager.getGridHitbox().getPreviousHitArea());
			} else {

				battleManager.useSkill(battleManager.getCurrentActor(), battleManager.getCurrentMonster(),
						battleManager.getCurrentSelectedSkill().getSkillPath());
				soundManager.setSoundByPathAndPlay("skill_" + battleManager.getCurrentSelectedSkill().getSkillPath());
				battleFlag.setUsingSkill(false);
			}
			battleFlag.setShowGrid(false);
		}
		resetHitboxState();
		return false;
	}

	private void resetHitboxState() {
		start = null;
		end = null;
		battleUi.getGridHitbox().hideAllTiles();
	}

	private void makeTurnBackgroundImage() {
		currentAttackerBackground = new Image(textureManager.getTexture("battleui_turntable_01"));
		turnTableBackground = new Image(textureManager.getTexture("battleui_turntable_02"));
	}

	private void makeBattleTurnImage() {
		Monster currentMonster = battleInfo.getCurrentMonster();
		turnBigImageMap.put(currentMonster.getFacePath(),
				new Image(textureManager.getBigBattleImage(currentMonster.getFacePath())));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnBigImageMap.put(hero.getFacePath(), new Image(textureManager.getBigBattleImage(hero.getFacePath())));
		}
		turnSmallImageMap.put(currentMonster.getFacePath(),
				new Image(textureManager.getSmallBattleImage(currentMonster.getFacePath())));
		for (Hero hero : partyManager.getBattleMemberList()) {
			turnSmallImageMap
					.put(hero.getFacePath(), new Image(textureManager.getSmallBattleImage(hero.getFacePath())));
		}
	}
}