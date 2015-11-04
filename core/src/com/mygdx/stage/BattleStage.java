package com.mygdx.stage;

import java.util.HashMap;
import java.util.Queue;

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
import com.mygdx.enums.BattleStateEnum;
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
import com.mygdx.model.item.Weapon;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;

public class BattleStage extends BaseOneLevelStage {
	@Autowired
	private MusicManager musicManager;
	private final String START_BATTLE_MESSAGE = "전투 시작!";
	private final int NORMAL_ATTACK = 30;

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
	private Monster selectedMonster;
	private Queue<Unit> orderedUnits;

	private float animationDelay;
	private final float MONSTER_ATTACK_DELAY = 1.5f;
	// Image
	private Image currentAttackerBackground;
	private Image turnTableBackground;
	private TextureRegion textMenu;
	private HashMap<String, Image> turnBigImageMap = new HashMap<String, Image>();
	private HashMap<String, Image> turnSmallImageMap = new HashMap<String, Image>();
	// Table
	private Table imageTable = new Table();
	private Table bigImageTable = new Table();
	private Table smallImageTable = new Table();
	private Table turnTable = new Table();
	private Table textMenuTable = new Table();
	private Vector2 start, end;
	private Unit currentAttackUnit;
	private Label battleDescriptionLabel;

	@Override
	public void act(float delta) {
		super.act(delta);

		if (battleManager.getCurrentAttackUnit() instanceof Monster) {
			doMonsterTurn(delta);
		}

		if (animationManager.isPlayable()) {
			playBattleAction(delta);
		}

		if (start != null && end != null) {
			Gdx.gl.glLineWidth(6.0f);
			ShapeRenderer shapeRenderer = new ShapeRenderer();
			shapeRenderer.setProjectionMatrix(getCamera().combined);
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
			shapeRenderer.line(start, end);
			shapeRenderer.end();
		}

		if (battleManager.isSmallUpdate()) {
			updateSmallImageTable();
			battleManager.setSmallUpdate(false);
		}
		if (battleManager.isBigUpdate()) {
			updateBigImageTable();
			battleManager.setBigUpdate(false);
		}
		updateBattleDescriptionLabel();
		cameraManager.act(delta);
	}

	public Stage makeStage() {
		super.makeStage();
		battleManager.intiateAllProperties();
		battleManager.gameObjectPopup = new SkillRunPopup();
		if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)) {
			showInEncounterCase();
		}

		battleManager.updateOrder();
		currentAttackUnit = battleManager.getCurrentActors(); // 여기선 첫번째 턴
		selectedMonster = battleManager.getSelectedMonster();
		battleManager.setCurrentAttackUnit(currentAttackUnit);

		tableStack.add(makeTurnTable()); // TurnTable 배경 테이블
		tableStack.add(makeTurnFaceTable()); // TurnTable위에 있는 영웅들 이미지 테이블
		battleManager.setMonsterSize(MonsterEnum.SizeType.MEDIUM);

		tableStack.add(battleManager.getNowGridHitbox());
		battleManager.setShowGrid(false);
		battleManager.setBattleStage(this);

		return this;
	}

	private void showInEncounterCase() {
		soundManager.setSoundByPathAndPlay("notice_encount");
		battleManager.initializeBattleMember();
		showMenuBarAnimation();
		showBattleInfoMessage(START_BATTLE_MESSAGE);
	}

	private void showBattleInfoMessage(String battleInfoMessage) {
		battleManager.showBattleInfoMessage(battleInfoMessage);
	}

	private void doMonsterTurn(float delta) {
		battleManager.hideBattleCommandButtons();
		animationDelay += delta;
		if (animationDelay > MONSTER_ATTACK_DELAY) {
			Hero randomHero = partyManager.pickRandomHero();
			battleManager.calCostGague(battleManager.getCurrentAttackUnit(), NORMAL_ATTACK);
			battleManager.attack(battleManager.getCurrentAttackUnit(), randomHero, null);
			battleManager.endTurn();
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

	private Table makeTurnTable() {
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
		if (battleManager.getText() != null) {
			battleDescriptionLabel.setText(battleManager.getText());
		} else {
			battleDescriptionLabel.setText("상황을 기다리는 중 입니다");
		}
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
		battleManager.updateSubOrder();
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

	private int getWeaponHitboxSize() {
		Hero forInv = (Hero) battleManager.getCurrentAttackUnit();
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
		if (battleManager.isShowGrid()) {
			end = (new Vector2(touched.x, touched.y));
			if (battleManager.getNowGridHitbox().isInsideHitbox(touched.x, touched.y)) {
				if (battleManager.isSkill()) {
					battleManager.setGridLimitNum(battleManager.getCurrentSelectedSkill().getHitboxSize());
				} else {
					battleManager.setGridLimitNum(getWeaponHitboxSize());
				}
				start = (new Vector2(touched.x, touched.y));
				battleManager.getNowGridHitbox().setStartPosition(touched.x, touched.y);
				battleManager.getNowGridHitbox().showTileWhereMoved(touched.x, touched.y);
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		super.touchDragged(screenX, screenY, pointer);
		if (battleManager.isShowGrid()) {
			end = (new Vector2(touched.x, touched.y));
			if (!battleManager.isSkill()) {
				battleManager.getNowGridHitbox().showTileWhereMoved(touched.x, touched.y);
			} else {
				if (battleManager.getNowGridHitbox().getHitboxCenter() == null) {
					battleManager.getNowGridHitbox().showTileWhereMoved(touched.x, touched.y);
				} else {
					battleManager.getNowGridHitbox().showFixedTilesAt(touched.x, touched.y);
				}
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		if (battleManager.isShowGrid() && battleManager.getNowGridHitbox().isInsideHitbox(touched.x, touched.y)) {
			if (!battleManager.isSkill()) {
				soundManager.setSoundByPathAndPlay("slash");
				battleManager.attack(battleManager.getCurrentAttackUnit(), selectedMonster, battleManager
						.getNowGridHitbox().getPreviousHitArea());
			} else {

				battleManager.useSkill(battleManager.getCurrentAttackUnit(), selectedMonster, battleManager
						.getCurrentSelectedSkill().getSkillPath());
				soundManager.setSoundByPathAndPlay("skill_" + battleManager.getCurrentSelectedSkill().getSkillPath());
				battleManager.setSkill(false);
			}
			battleManager.setShowGrid(false);
		}
		resetHitboxState();
		return false;
	}

	private void resetHitboxState() {
		start = null;
		end = null;
		battleManager.getNowGridHitbox().hideAllTiles();
	}

	private void makeTurnBackgroundImage() {
		currentAttackerBackground = new Image(textureManager.getTexture("battleui_turntable_01"));
		turnTableBackground = new Image(textureManager.getTexture("battleui_turntable_02"));
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
			turnSmallImageMap
					.put(hero.getFacePath(), new Image(textureManager.getSmallBattleImage(hero.getFacePath())));
		}
	}
}
