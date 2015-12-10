package com.mygdx.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.battle.BattleFlag;
import com.mygdx.battle.BattleInfo;
import com.mygdx.battle.BattleUi;
import com.mygdx.battle.Buff;
import com.mygdx.battle.Skill;
import com.mygdx.controller.BattleCommandController;
import com.mygdx.enums.BattleCommandEnum;
import com.mygdx.enums.BattleMessages;
import com.mygdx.enums.BattleSituationEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.SkillTargetUnitEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;
import com.mygdx.screen.BattleScreen;
import com.mygdx.stage.BattleStage;
import com.mygdx.ui.GridHitbox;
import com.mygdx.unitStrategy.CostGaugeStrategy;
import com.mygdx.unitStrategy.DropItemStrategy;

public class BattleManager {
	public static final int TIME_FLOW_RATE = 1;
	private final String TAG = "BattleManager";
	private static final Set<String> SKIP_ACTION_STUN_TYPES = new HashSet<String>();
	static {
		SKIP_ACTION_STUN_TYPES.add("buff_de_sturn");
		SKIP_ACTION_STUN_TYPES.add("buff_de_frozen");
		SKIP_ACTION_STUN_TYPES.add("buff_de_sleep");
	}
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private BagManager bagManager;
	@Autowired
	private SkillAssets skillAssets;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private AnimationManager animationManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private UnitManager unitManager;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private QuestManager questManager;
	private CostGaugeStrategy costGaugeStrategy;
	private DropItemStrategy dropItemStrategy;
	private List<Unit> battleList;
	@Autowired
	private BattleCommandController battleCommandController;

	private BattleInfo battleInfo;
	private BattleUi battleUi;
	private BattleFlag battleFlag;

	public BattleManager() {
		this.battleInfo = new BattleInfo();
		this.battleUi = new BattleUi();
		this.battleFlag = new BattleFlag();
		this.costGaugeStrategy = new CostGaugeStrategy();
	}

	public void startBattleWithMonster(Monster selectedMonster) {
		unitManager.initiateMonster(selectedMonster);
		if (getBattleInfo().getCurrentBattleSituation().equals(BattleSituationEnum.NOT_IN_BATTLE)) {
			setCurrentBattleSituation(BattleSituationEnum.ENCOUNTER);
		}
		setCurrentMonster(selectedMonster);
		screenFactory.show(ScreenEnum.BATTLE);
	}

	private void checkStunOverUnit() {
		Iterator<Unit> stunnedUnitIterator = battleInfo.getStunnedUnitList().iterator();
		while (stunnedUnitIterator.hasNext()) {
			Unit beforeStunnedUnit = stunnedUnitIterator.next();
			if (!hasStunTypeBuff(beforeStunnedUnit)) {
				battleInfo.getStunnedUnitList().remove(beforeStunnedUnit);
				battleInfo.getBattleQueue().add(beforeStunnedUnit);
			}
		}
	}

	public void turnOver() {
		PriorityQueue<Unit> battleQueue = battleInfo.getBattleQueue();
		checkStunOverUnit();
		if (!battleQueue.isEmpty()) {
			Unit reorderedUnit = battleQueue.poll();
			battleQueue.add(reorderedUnit);
			for (Iterator<Unit> itr = battleQueue.iterator(); itr.hasNext();) {
				Unit checkUnit = itr.next();
				if (hasStunTypeBuff(checkUnit)) {
					if (!battleQueue.isEmpty()) {
						itr.remove();
						battleInfo.getStunnedUnitList().add(checkUnit);
					} else {
						break;
					}
				}
			}
		}
	}

	public void handleTurnEnd() {
		plusTurnGauge();
		for (Unit buffUnit : getBattleList()) {
			buffUnit.getBattleStrategy().runBuffEffect(buffUnit);
		}
		setSmallTurnTableUpdate(true);
		turnOver();
		setBigTurnTableUpdate(true);
		setSmallTurnTableUpdate(true);
		setUsingSkill(false);
		setMonsterTurnEnd(true);
		battleCommandController.setBattleCommand(BattleCommandEnum.NO_COMMAND);
	}

	private void setMonsterTurnEnd(boolean b) {
		battleFlag.setMonsterTurnEnd(true);
	}

	public boolean hasStunTypeBuff(Unit unit) {
		for (Buff buff : unit.getBuffList()) {
			String buffPath = buff.getBuffPath();
			if (SKIP_ACTION_STUN_TYPES.contains(buffPath)) {
				return true;
			}
		}
		return false;
	}

	public void setBattleCommandButtonClickState() {
		setShowGrid(false);
		getCurrentActor().setGauge(getCurrentActor().getPreGauge());
		timeManager.plusSecond(-timeManager.getPreTime());
	}

	public void runAway() {
		int randomRunValue = (int) Math.round(Math.random() * 100);
		if (getRunPercent() > randomRunValue) {
			setCurrentBattleSituation(BattleSituationEnum.NOT_IN_BATTLE);
			finalizeBattle();
			movingManager.goCurrentLocatePosition();
			Gdx.app.log(TAG, "도망!");
		} else {
			checkTurnEnd();
			Gdx.app.log(TAG, "도망 실패!");
		}
	}

	public void applyGauge(int useGauge) {
		costGaugeStrategy.applyCostGaugeToUnit(getCurrentActor(), useGauge);
		timeManager.setPreTime(costGaugeStrategy.getCostGauge() * TIME_FLOW_RATE);
		timeManager.plusSecond(costGaugeStrategy.getCostGauge() * TIME_FLOW_RATE);
	}

	public void useSkill(Unit attackUnit, Unit targetUnit, String skillName) {
		Skill skill = skillAssets.getSkill(skillName);
		ArrayList<Unit> targetList = getSkillTargetUnitList(skill.getSkillTargetType(), attackUnit, targetUnit);
		attackUnit.getStatus().setCasting(attackUnit.getStatus().getCasting() - skill.getCostCasting());
		checkCastingValue(attackUnit);
		attackUnit.useSkill(targetList, skill);
		animationManager.registerAnimation(attackUnit, targetUnit, skillName);
	}

	public void checkCastingValue(Unit attackUnit) {
		if (attackUnit.getStatus().getCasting() == 0) {
			Buff overload = skillAssets.getBuff("overload");
			attackUnit.setOverload(0);
			attackUnit.getBuffList().remove(overload);
		}
	}

	private void plusTurnGauge() {
		int maxGauge = 0;
		for (Unit unit : getBattleList()) {
			if (maxGauge <= unit.getGauge()) {
				maxGauge = unit.getGauge();
			}
		}

		for (Unit unit : getBattleList()) {
			unit.setGauge(unit.getGauge() + 100 - maxGauge);
		}
	}

	public ArrayList<Unit> getSkillTargetUnitList(String targetType, Unit skillUser, Unit selectedUnit) {
		ArrayList<Unit> list = new ArrayList<Unit>();
		SkillTargetUnitEnum skillTargetUnitEnum = SkillTargetUnitEnum.findSkillTargetEnum(targetType);
		switch (skillTargetUnitEnum) {
			case ALL :
				list.addAll(partyManager.getBattleMemberList());
				break;
			case MONSTER :
				list.add(battleInfo.getCurrentMonster());
				break;
			case ONE :
				list.add(selectedUnit);
				break;
			case RANDOM :
				Hero pick = getRandomHero();
				if (pick != null) {
					list.add(pick);
				}
				break;
			case SELF :
				list.add(skillUser);
				break;
			default :
				break;

		}
		return list;
	}

	public boolean isInBattle() {
		if (getBattleInfo().getCurrentBattleSituation().equals(BattleSituationEnum.NOT_IN_BATTLE)) {
			return false;
		} else {
			return true;
		}
	}

	private Hero getRandomHero() {
		Hero pick = null;
		ArrayList<Hero> aliveList = new ArrayList<Hero>();
		for (Hero hero : partyManager.getBattleMemberList()) {
			if (!isDead(hero)) {
				aliveList.add(hero);
			}
		}

		Random random = new Random();

		if (aliveList.size() == 0) {
			Gdx.app.log(TAG, "영웅 랜덤 선택 실패: 살아있는 영웅이 없음");
		} else {
			int randomIndex = random.nextInt(aliveList.size());
			pick = partyManager.getBattleMemberList().get(randomIndex);
		}

		return pick;
	}

	public boolean isBattleEnd() {
		boolean monsterState = isMonsterDead();
		boolean partyState = isHeroDead();
		if (monsterState && !partyState) {
			Gdx.app.log(TAG, "용사팀의 승리!");
			setCurrentBattleSituation(BattleSituationEnum.PLAYER_WIN);
			return true;
		} else if (partyState && !monsterState) {
			Gdx.app.log(TAG, "용사팀의 패배!");
			battleInfo.setCurrentBattleSituation(BattleSituationEnum.GAME_OVER);
			return true;
		} else if (partyState && monsterState) {
			Gdx.app.log(TAG, "잘못된 배틀 : 동시에 죽었다.");
			battleInfo.setCurrentBattleSituation(BattleSituationEnum.GAME_OVER);
			return true;
		} else {
			return false;
		}
	}

	private void finalizeBattle() {
		Buff casting = skillAssets.getBuff("casting");
		Buff overwork = skillAssets.getBuff("overwork");
		for (Unit unit : getBattleList()) {
			ArrayList<Buff> cancelList = new ArrayList<Buff>();
			unit.setGauge(100);
			unit.setSubValue(0);
			unit.setOverload(0);
			for (Buff buff : unit.getBuffList()) {
				if (buff.equals(casting) || buff.equals(overwork)) {

				} else {
					cancelList.add(buff);
				}
			}
			for (Buff removableBuff : cancelList) {
				unit.getBuffList().remove(removableBuff);
			}
		}
	}

	public void setCurrentBattleSituation(BattleSituationEnum battleSituationEnum) {
		battleInfo.setCurrentBattleSituation(battleSituationEnum);
		if (battleInfo.getCurrentBattleSituation().equals(BattleSituationEnum.IN_GAME)) {
			musicManager.setMusicAndPlay("bgm_battle");
		}
	}

	private boolean isDead(Unit unit) {
		return unit.getStatus().getHp() <= 1;
	}

	private boolean isHeroDead() {
		for (Hero hero : partyManager.getBattleMemberList()) {
			if (isDead(hero)) {
				return true;
			}
		}
		return false;
	}

	private boolean isMonsterDead() {
		return isDead(battleInfo.getCurrentMonster());
	}

	public Monster getCurrentMonster() {
		return battleInfo.getCurrentMonster();
	}

	public void setCurrentMonster(Monster currentMonster) {
		battleInfo.setCurrentMonster(currentMonster);
	}

	public void setCurrentSelectedSkill(Skill skill) {
		battleInfo.setCurrentSelectedSkill(skill);
	}

	public Skill getCurrentSelectedSkill() {
		return battleInfo.getCurrentSelectedSkill();
	}

	public boolean isShowGrid() {
		return battleFlag.isShowGrid();
	}

	public void setShowGrid(boolean showGrid) {
		if (showGrid) {
			battleUi.getGridHitbox().setVisible(true);
		} else {
			battleUi.getGridHitbox().setVisible(false);
		}

		battleFlag.setShowGrid(showGrid);
	}

	public PriorityQueue<Unit> getBattleQueue() {
		return battleInfo.getBattleQueue();
	}

	public void setBattleQueue(PriorityQueue<Unit> battleQueue) {
		battleInfo.setBattleQueue(battleQueue);
	}

	public boolean isSmallTurnTableUpdate() {
		return battleFlag.isSmallUpdate();
	}

	public void setSmallTurnTableUpdate(boolean isUpdate) {
		battleFlag.setSmallUpdate(isUpdate);
	}

	public boolean isBigTurnTableUpdate() {
		return battleFlag.isBigUpdate();
	}

	public void setBigTurnTableUpdate(boolean isBigUpdate) {
		battleFlag.setBigUpdate(isBigUpdate);
	}

	public int getRunPercent() {
		return battleInfo.getRunPercent();
	}

	public void setRunPercent(int runPercent) {
		battleInfo.setRunPercent(runPercent);
	}

	public boolean isUsingSkill() {
		return battleFlag.isUsingSkill();
	}

	public void setUsingSkill(boolean isSkill) {
		battleFlag.setUsingSkill(isSkill);
	}

	public GridHitbox getGridHitbox() {
		return battleUi.getGridHitbox();
	}

	public void setMonsterSize(MonsterEnum.SizeType type) {
		battleUi.getGridHitbox().setTextureManager(textureManager);
		battleUi.getGridHitbox().setUiConstantsMap(constantsAssets);
		battleUi.getGridHitbox().setSizeType(type);
	}

	public int getGridLimitNum() {
		return battleUi.getGridHitbox().getLimitNum();
	}

	public void setGridLimitNum(int num) {
		battleUi.getGridHitbox().setLimitNum(num);
	}

	public Item getCurrentSelectedItem() {
		return battleInfo.getCurrentSelectedItem();
	}

	public void setCurrentSelectedItem(Item currentSelectedItem) {
		battleInfo.setCurrentSelectedItem(currentSelectedItem);
	}

	public List<Buff> getMonsterBuffList() {
		return getCurrentMonster().getBuffList();
	}

	public String getBattleDescriptionLabel() {
		return battleInfo.getBattleDescriptionLabel();
	}

	public void setBattleDescriptionLabel(String text) {
		battleInfo.setBattleDescriptionLabel(text);
	}

	public boolean isEventBattle() {
		return battleFlag.isEventBattle();
	}

	public void setBattleStage(BattleStage stage) {
		battleUi.setBattleStage(stage);
	}

	public void setEventBattle(boolean isEventBattle) {
		battleFlag.setEventBattle(isEventBattle);
	}

	public void setBackgroundPath(String backgroundPath) {
		battleInfo.setBackgroundPath(backgroundPath);
	}

	public String getBackgroundPath() {
		return battleInfo.getBackgroundPath();
	}

	public void setBattleCommandButtonList(ArrayList<ImageButton> battleCommandButtonList) {
		battleUi.setBattleCommandButtonList(battleCommandButtonList);
	}

	public void setEndBuff(boolean isEndBuff) {
		battleFlag.setEndBuff(isEndBuff);
	}

	public boolean isEndBuff() {
		return battleFlag.isEndBuff();
	}

	public void getExperience(Monster selectedMonster) {
		for (Unit unit : partyManager.getBattleMemberList()) {
			unit.getStatus().setExperience(
					unit.getStatus().getExperience() + selectedMonster.getStatus().getExperience());
		}
		setBattleDescriptionLabel("경험치 " + selectedMonster.getStatus().getExperience() + "을 얻었다!");
		partyManager.calculateLevel();
	}

	private void getDropItem(Monster selectedMonster) {
		Item item = dropItemStrategy.makeDropItem(selectedMonster);
		bagManager.putItemInBag(item);
		setGetItem(true);
	}

	public void battleCommandMoveAction() {
		getBattleCommandButtonTable().addAction(Actions.moveTo(1720, 15));
		getBattleCommandButtonTable().addAction(Actions.moveTo(1720, 15, 1));
	}

	public String getBattleInfoMessage() {
		return battleInfo.getBattleStateInfoLabel();
	}

	public void setBattleInfoMessage(String battleInfoMessage) {
		battleInfo.setBattleStateInfoLabel(battleInfoMessage);
	}

	public FieldTypeEnum getFieldType() {
		return battleInfo.getFieldType();
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		battleInfo.setFieldType(fieldType);
	}

	public Table getBattleCommandButtonTable() {
		return battleUi.getBattleCommandButtonTable();
	}

	public void setBattleCommandButtonTable(Table battleCommandButtonTable) {
		battleUi.setBattleCommandButtonTable(battleCommandButtonTable);
	}

	public boolean isGetItem() {
		return battleFlag.isGetItem();
	}

	public void setGetItem(boolean isGetItem) {
		setGetItem(isGetItem);
	}

	public Item getDropItem() {
		return battleInfo.getDropItem();
	}

	public void setDropItem(Item dropItem) {
		battleInfo.setDropItem(dropItem);
	}

	public ArrayList<ImageButton> getBattleCommandButtonList() {
		return battleUi.getBattleCommandButtonList();
	}

	public void initializeBattle(List<Hero> battleHeroList, Monster monster) {
		List<Unit> battleList = new ArrayList<>();
		battleList.addAll(battleHeroList);
		battleList.add(monster);
		setBattleList(battleList);
		setMonsterTurnEnd(true);
		PriorityQueue<Unit> battleQueue = new PriorityQueue<Unit>();
		battleQueue.addAll(battleList);
		setBattleQueue(battleQueue);
		setBattleDescriptionLabel(monster.getName() + BattleMessages.MEET_MESSAGE);

		for (Unit battleListUnit : battleList) {
			battleListUnit.setGauge(100);
			battleListUnit.setPreGauge(100);
			battleListUnit.setAggro(100);
			battleListUnit.setRealAggro(100);
			battleListUnit.setSubValue(0);
			battleListUnit.setOverload(0);
			battleListUnit.setActingPower(-24000 / (battleListUnit.getStatus().getSpeed() + 300) + 160);
			battleListUnit.setRealStatus(battleListUnit.getStatus());
		}
		monster.getStatus().setHp(monster.getStatus().getMaxHp());
		monster.setRealStatus(monster.getStatus());
		battleCommandController.setBattleCommand(BattleCommandEnum.NO_COMMAND);
		setCurrentBattleSituation(BattleSituationEnum.IN_GAME);
	}

	public void playBattleAction(float delta) {
		battleFlag.setMonsterTurnEnd(false);
		animationManager.nextFrame(delta);
		if (animationManager.isEmptyAnimation()) {
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_COMMAND, "normal_attack");
			if (getCurrentSelectedSkill() != null) {
				storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_COMMAND, "skill_attack");
			}
		}

	}

	public Unit getCurrentActor() {
		return battleInfo.getBattleQueue().peek();
	}

	public void setGridHitbox(GridHitbox gridHitbox) {
		battleUi.setGridHitbox(gridHitbox);
	}

	public List<Unit> getBattleList() {
		return battleList;
	}

	public void setBattleList(List<Unit> battleList) {
		this.battleList = battleList;
	}

	public void showBattleInfoMessage(String battleInfoMessage) {
		setBattleInfoMessage(battleInfoMessage);
		BattleScreen.showBattleInfoMessage = true;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}

	public BattleUi getBattleUi() {
		return battleUi;
	}

	public void setBattleUi(BattleUi battleUi) {
		this.battleUi = battleUi;
	}

	public BattleFlag getBattleFlag() {
		return battleFlag;
	}

	public void setBattleFlag(BattleFlag battleFlag) {
		this.battleFlag = battleFlag;
	}

	public SkillRunPopup getGameObjectPopup() {
		return battleUi.getSkillRunPopup();
	}

	public void setGameObjectPopup(SkillRunPopup gameObjectPopup) {
		battleUi.setSkillRunPopup(gameObjectPopup);
	}

	public CostGaugeStrategy getCostGaugeStrategy() {
		return costGaugeStrategy;
	}

	public void setCostGaugeStrategy(CostGaugeStrategy costGaugeStrategy) {
		this.costGaugeStrategy = costGaugeStrategy;
	}

	public static float getWindowHeight() {
		return StaticAssets.windowHeight;
	}

	public static float getWindowWidth() {
		return StaticAssets.windowWidth;
	}

	public void setBattleCommand(BattleCommandEnum battleCommandEnum) {
		battleCommandController.setBattleCommand(battleCommandEnum);
		setBattleCommandButtonClickState();
		applyGauge(battleCommandEnum.getCostGauge());
		setShowGrid(true);
	}

	public void doBattleCommand(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		battleCommandController.doBattleCommand(attackUnit, defendUnit, hitArea);
	}

	public BattleCommandController getBattleCommandController() {
		return battleCommandController;
	}

	public void setBattleCommandController(BattleCommandController battleCommandController) {
		this.battleCommandController = battleCommandController;
	}

	public BattleInfo getBattleInfo() {
		return battleInfo;
	}

	public void setBattleInfo(BattleInfo battleInfo) {
		this.battleInfo = battleInfo;
	}

	public void checkTurnEnd() {
		if (isBattleEnd()) {
			processWhenBattleEnd();
		} else {
			handleTurnEnd();
			setCurrentSelectedSkill(null);
		}
	}

	private void processWhenBattleEnd() {
		if (getBattleInfo().equals(BattleSituationEnum.PLAYER_WIN)) {
			questManager.checkHuntQuest(battleInfo.getCurrentMonster().getFacePath());
			setCurrentBattleSituation(BattleSituationEnum.NOT_IN_BATTLE);
			musicManager.stopMusic();
			soundManager.setSoundByPathAndPlay("notice_victory");
			getDropItem(battleInfo.getCurrentMonster());
			getExperience(battleInfo.getCurrentMonster());
			showBattleInfoMessage(BattleMessages.PLAYER_WIN_MESSAGE.toString());
			finalizeBattle();
		} else {
			finalizeBattle();
			screenFactory.show(ScreenEnum.GAME_OVER);
		}
	}
}
