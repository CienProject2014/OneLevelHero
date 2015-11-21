package com.mygdx.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.BattleCommandEnum;
import com.mygdx.enums.BattleMessages;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.SkillTargetEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.item.Consumables;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;
import com.mygdx.screen.BattleScreen;
import com.mygdx.stage.BattleStage;
import com.mygdx.ui.GridHitbox;

public class BattleManager {
	private final String TAG = "BattleManager";
	private final int TIME_FLOW_RATE = 1;
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
	private List<Unit> battleMemberList;

	private BattleStateEnum currentBattleState;
	private GridHitbox gridHitbox; // grid hitbox 테이블
	private BattleStage battleStage;
	private Unit currentAttackUnit;
	private String battleDescriptionLabel;
	private String backgroundPath;
	public SkillRunPopup gameObjectPopup;
	private BattleCommandEnum currentUsingCommand;
	private Monster selectedMonster;
	private String battleInfoMessage;
	private ArrayList<ImageButton> rMenuButtonList;
	private FieldTypeEnum fieldType;
	private Table rMenuTable;
	private boolean isGetItem;
	private Item dropItem;
	private Skill currentSelectedSkill;
	private boolean isEndBuff;
	private boolean isEventBattle;
	private Hero currentActor;
	private PriorityQueue<Unit> orderedUnits;
	private boolean isSmallUpdate;
	private boolean isBigUpdate;
	private int runPercent;
	private boolean isSkill;
	private Item currentSelectedItem;
	private boolean isShowGrid;

	public void startBattleWithMonster(Monster selectedMonster) {
		unitManager.initiateMonster(selectedMonster);
		if (getBattleState().equals(BattleStateEnum.NOT_IN_BATTLE)) {
			setBattleState(BattleStateEnum.ENCOUNTER);
		}
		setSelectedMonster(selectedMonster);
		screenFactory.show(ScreenEnum.BATTLE);
	}

	public Unit getPickedActor(PriorityQueue<Unit> orderedUnits) {
		Unit currentAttackUnit = orderedUnits.poll();
		while (hasStunTypeBuff(currentAttackUnit)) {
			getOrderedUnits().add(currentAttackUnit);
			currentAttackUnit = getOrderedUnits().poll();
		}
		setCurrentAttackUnit(currentAttackUnit);
		return currentAttackUnit;
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

	public void setStateByCurrentUsingCommand() {
		switch (getCurrentUsingCommand()) {
			case NO_COMMAND :
				setShowGrid(false);
				getCurrentActor().setGauge(getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case USE_SKILL :
				setSkill(false);
				setShowGrid(false);
				getCurrentActor().setGauge(getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case USE_ITEM :
				setShowGrid(false);
				getCurrentActor().setGauge(getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case DEFEND :
				setShowGrid(false);
				getCurrentActor().setGauge(getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case WAIT :
				setShowGrid(false);
				getCurrentActor().setGauge(getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case RUN_AWAY :
				setShowGrid(false);
				getCurrentActor().setGauge(getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			default :
				break;
		}
	}

	public void endTurn() {
		setCurrentAttackUnit(getPickedActor(orderedUnits));
		setBigUpdate(true);
		setSmallUpdate(true);
		showBattleCommandButtons();
		setCurrentUsingCommand(BattleCommandEnum.NO_COMMAND);
	}

	public void runAway() {
		int nGetVal = (int) Math.round(Math.random() * 100);
		if (getRunPercent() > nGetVal) {
			setBattleState(BattleStateEnum.NOT_IN_BATTLE);
			initBattle();
			movingManager.goCurrentLocatePosition();
			Gdx.app.log(TAG, "도망!");
		} else {
			Gdx.app.log(TAG, "도망 실패!");
			gameObjectPopup.setVisible(false);
			endTurn();
		}
	}

	public void attack(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		attackUnit.attack(defendUnit, hitArea);
		int centerX, centerY;
		centerX = getCenterToHitAreaX(hitArea);
		centerY = getCenterToHitAreaY(hitArea);
		if (attackUnit instanceof Hero) {
			readyForPlayerAnimation("empty hit", centerX, 4 - centerY, (int) (StaticAssets.windowHeight * 0.5f),
					(int) (StaticAssets.windowHeight * 0.5f));
		} else {
			soundManager.setSoundByPathAndPlay("attack_cutting");
			readyForMonsterAnimation((Hero) defendUnit, "empty hit", (int) (StaticAssets.windowHeight * 0.3f),
					(int) (StaticAssets.windowHeight * 0.3f));
		}
		checkIsBattleEnd();
	}

	public void updateGauge(int useGague) {
		calCostGague(getCurrentAttackUnit(), useGague);
		setSmallUpdate(true);
	}

	public void useSkill(Unit attackUnit, Unit targetUnit, String skillName) {
		Buff overload = skillAssets.getBuff("overload");
		Skill skill = skillAssets.getSkill(skillName);
		ArrayList<Unit> targetList = getTargetList(skill.getSkillTargetType(), attackUnit, targetUnit);
		attackUnit.getStatus().setCasting(attackUnit.getStatus().getCasting() - skill.getCostCasting());

		if (attackUnit.getStatus().getCasting() == 0) {
			attackUnit.setOverload(0);
			attackUnit.getBuffList().remove(overload);
		}
		attackUnit.useSkill(targetList, skill);
		if (attackUnit instanceof Hero) {
			readyForPlayerAnimation(skillName, (int) (StaticAssets.windowHeight * 0.8f),
					(int) (StaticAssets.windowHeight * 0.8f));
		} else {
			readyForMonsterAnimation((Hero) targetUnit, "attack_cutting", (int) (StaticAssets.windowHeight * 0.3f),
					(int) (StaticAssets.windowHeight * 0.3f));
		}

		checkIsBattleEnd();
	}

	public void calCostGague(Unit unit, int typeOfAction) {
		unit.setPreGague(unit.getGauge());
		int costGague = (int) (((double) (150 - unit.getActingPower()) / 50) * typeOfAction);
		if (this.isSkill()) {
			if (this.getCurrentSelectedSkill().getSkillPath().equals("flying")) {
				costGague += 15;
			}
			if (this.getCurrentSelectedSkill().getSkillPath().equals("back_of_sword")) {
				costGague += 25;
			}
		}
		unit.setGauge(unit.getGauge() - costGague);
		timeManager.setPreTime(costGague * TIME_FLOW_RATE);
		timeManager.plusSecond(costGague * TIME_FLOW_RATE);

		healGague();

		for (Unit buffUnit : getBattleMemberList()) {
			buffUnit.getBattleStrategy().runBuffEffect(buffUnit);
		}
	}

	public void fuckingCostGague(Unit unit, int typeOfAction) {
		unit.setPreGague(unit.getGauge());
		unit.setGauge(unit.getGauge() - typeOfAction);
		timeManager.setPreTime(typeOfAction * TIME_FLOW_RATE);
		timeManager.plusSecond(typeOfAction * TIME_FLOW_RATE);
		healGague();
	}

	public void useItem() {
		if (getCurrentSelectedItem() instanceof Consumables) {
			Consumables potion = (Consumables) getCurrentSelectedItem();
			Unit unit = getCurrentAttackUnit();
			if ((unit.getStatus().getHp() + potion.getHeal() < unit.getStatus().getMaxHp())) {
				unit.getStatus().setHp(unit.getStatus().getHp() + potion.getHeal());
			} else {
				unit.getStatus().setHp(unit.getStatus().getMaxHp());
			}
		}
	}

	public void doWaitCommand() {
		int maxGague = 0;
		int maxSubValue = 0;
		for (Unit unit : partyManager.getBattleMemberList()) {
			if (getCurrentAttackUnit() == unit) {
			} else {
				if (maxGague <= unit.getGauge()) {
					maxGague = unit.getGauge();
				}
			}
		}

		if ((100 - maxGague) == 0) {
			getCurrentAttackUnit().setSubValue(getCurrentAttackUnit().getSubvalue() + 1);
		} else {
			getCurrentAttackUnit().setSubValue(1);
		}

		fuckingCostGague(getCurrentAttackUnit(), 100 - maxGague);

		for (Unit unit : partyManager.getBattleMemberList()) {
			if (getCurrentAttackUnit() == unit) {
			} else {
				if (maxSubValue <= unit.getSubvalue()) {
					maxSubValue = unit.getSubvalue();
				}
			}
		}
		getCurrentAttackUnit().setSubValue(maxSubValue + 1);
		if (getCurrentAttackUnit().getSubvalue() == 3) {
			fuckingCostGague(getCurrentAttackUnit(), 1);
			getCurrentAttackUnit().setSubValue(0);
		}
	}

	private void healGague() {
		int maxGague = 0;
		for (Unit unit : getBattleMemberList()) {
			if (maxGague <= unit.getGauge()) {
				maxGague = unit.getGauge();
			}
		}

		for (Unit unit : getBattleMemberList()) {
			unit.setGauge(unit.getGauge() + 100 - maxGague);
		}
	}

	public void hideBattleCommandButtons() {
		for (ImageButton rMenuButton : getRMenuButtonList()) {
			rMenuButton.setVisible(false);
			rMenuButton.setTouchable(Touchable.disabled);
		}
		getRMenuTable().setVisible(false);
	}

	public void showBattleCommandButtons() {
		for (ImageButton rMenuButton : getRMenuButtonList()) {
			rMenuButton.setVisible(true);
			rMenuButton.setTouchable(Touchable.enabled);
		}
		getRMenuTable().setVisible(true);
		getRMenuTable().addAction(Actions.moveTo(1720, 15));
		getRMenuTable().addAction(Actions.moveTo(1720, 15, 1));
		gameObjectPopup.setVisible(false);
	}

	private ArrayList<Unit> getTargetList(String targetType, Unit skillUser, Unit selectedUnit) {
		ArrayList<Unit> list = new ArrayList<Unit>();
		SkillTargetEnum enm = SkillTargetEnum.findSkillTargetEnum(targetType);
		switch (enm) {
			case ALL :
				list.addAll(partyManager.getBattleMemberList());
				break;
			case MONSTER :
				list.add(selectedMonster);
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
		if (getBattleState().equals(BattleStateEnum.NOT_IN_BATTLE)) {
			return false;
		} else {
			return true;
		}
	}

	public void readyForMonsterAnimation(Hero defendHero, String animationName, int width, int height) {
		int playerOrder = getPlayerOrder(defendHero);
		final int x;
		final int y;
		switch (playerOrder) {
			case 0 :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.6f);
				break;
			case 1 :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.5f);
				break;
			case 2 :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.4f);
				break;
			default :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.6f);
				break;
		}

		// animationManager.registerAnimation("attack_cutting", x, y);
		animationManager.registerAnimation("attack_cutting", x, y, width, height);
	}

	public int getPlayerOrder(Hero defendHero) {
		for (int i = 0; i < partyManager.getBattleMemberList().size(); i++) {
			if (partyManager.getBattleMemberList().get(i).equals(defendHero)) {
				return i;
			}
		}
		Gdx.app.log("BattleManager", "파티 index정보 오류");
		return -1;
	}

	/***
	 * readyForPlayerAnimation 애니메이션을 등록하여 애니메이션을 작동할 준비를 한다. 가운데에서 애니메이션이 실행된다.
	 * 
	 * @param animationName
	 *            등록할 애니메이션의 이름
	 */
	public void readyForPlayerAnimation(String animationName, int width, int height) {

		if (partyManager.getCurrentSelectedHero() == null) {
			// 팀원을 누르는 경우가 아닐때는 받아온 경로로 실행시킨다.
			int x = (int) (StaticAssets.windowWidth / 2);
			int y = (int) (StaticAssets.windowHeight / 2);
			animationManager.registerAnimation(animationName, x, y, width, height);
		} else {
			int playerOrder = getPlayerOrder(partyManager.getCurrentSelectedHero());
			int x1;
			int y1;
			switch (playerOrder) {
				case 0 :
					x1 = (int) (StaticAssets.windowWidth * 0.1f);
					y1 = (int) (StaticAssets.windowHeight * 0.6f);
					break;
				case 1 :
					x1 = (int) (StaticAssets.windowWidth * 0.1f);
					y1 = (int) (StaticAssets.windowHeight * 0.5f);
					break;
				case 2 :
					x1 = (int) (StaticAssets.windowWidth * 0.1f);
					y1 = (int) (StaticAssets.windowHeight * 0.4f);
					break;
				default :
					x1 = (int) (StaticAssets.windowWidth * 0.1f);
					y1 = (int) (StaticAssets.windowHeight * 0.6f);
					break;
			}
			// animationManager.registerAnimation("attack_cutting", x, y);
			animationManager.registerAnimation("attack_cutting", x1, y1, width, height);
		}

	}

	/***
	 * readyForPlayerAnimation 애니메이션을 등록하여 애니메이션을 작동할 준비를 한다. 위치를 지정할 수 있다.
	 * 
	 * @param animationName
	 *            등록할 애니메이션의 이름
	 * @param x
	 *            25개의 칸 중 애니메이션이 재생될 x위치 (0 <= x <= 4)
	 * @param y
	 *            25개의 칸 중 애니메이션이 재생될 y위치 (0 <= y <= 4)
	 * @param width
	 *            애니메이션이 실행될 폭
	 * @param height
	 *            애니메이션이 실행될 높이
	 */
	public void readyForPlayerAnimation(String animationName, int x, int y, int width, int height) {
		int localx = (int) (StaticAssets.windowWidth * 0.5f) + (int) (StaticAssets.windowHeight * (-0.24f + 0.12f * x));
		int localy = (int) (StaticAssets.windowHeight * 0.5f)
				+ (int) (StaticAssets.windowHeight * (-0.24f + 0.12f * y));
		animationManager.registerAnimation(animationName, localx, localy, width, height);
		battleStage.getCameraManager().shaking();
	}

	/**
	 * readyForPlayerAnimation 애니메이션을 등록하여 애니메이션을 작동할 준비를 한다
	 * 
	 * @param animationName
	 *            등록할 애니메이션의 이름
	 * @param startX
	 *            25개의 칸 중 첫번째로 입력된 칸의 x위치 (0 <= value <= 4)
	 * @param startY
	 *            25개의 칸 중 첫번째로 입력된 칸의 y위치 (0 <= value <= 4)
	 * @param endX
	 *            25개의 칸 중 마지막으로 입력된 칸의 x위치 (0 <= value <= 4)
	 * @param endY
	 *            25개의 칸 중 마지막으로 입력된 칸의 y위치 (0 <= value <= 4)
	 */
	public void readyForPlayerAnimation(String animationName, int startX, int startY, int endX, int endY, int width,
			int height) {
		readyForPlayerAnimation(animationName, (startX + endX) / 2, (startY + endY) / 2, width, height);
	}

	public void setCurrentActor(Hero hero) {
		this.currentActor = hero;
	}

	public Unit getCurrentActor() {
		Unit currentAttackUnit = getOrderedUnits().peek();
		if (currentAttackUnit instanceof Hero) {
			setCurrentActor((Hero) currentAttackUnit);
		}
		return currentActor;
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

	private int getCenterToHitAreaX(int[][] hit) {
		if (hit != null) {
			int ret = 0;
			int count = 0;
			for (int i = 0; i < hit.length; i++) {
				for (int j = 0; j < hit[i].length; j++) {
					if (hit[i][j] != 0) {
						ret += j;
						count++;
					}
				}
			}
			if (count == 0) {
				return 0;
			}
			return (int) (ret / count);
		}
		return 0;
	}

	private int getCenterToHitAreaY(int[][] hit) {
		if (hit != null) {
			int ret = 0;
			int count = 0;
			for (int i = 0; i < hit.length; i++) {
				for (int j = 0; j < hit[i].length; j++) {
					if (hit[i][j] != 0) {
						ret += i;
						count++;
					}
				}
			}
			if (count == 0) {
				return 0;
			}
			return (int) (ret / count);
		}
		return 0;
	}

	private void checkIsBattleEnd() {
		boolean monsterState = isMonsterDead();
		boolean partyState = isHeroDead();
		if (monsterState && !partyState) {
			Gdx.app.log(TAG, "용사팀의 승리!");
			initBattle();
			setBattleState(BattleStateEnum.PLAYER_WIN);
		} else if (partyState && !monsterState) {
			Gdx.app.log(TAG, "용사팀의 패배!");
			initBattle();
			setBattleState(BattleStateEnum.GAME_OVER);
		} else if (partyState && monsterState) {
			Gdx.app.log(TAG, "잘못된 배틀 : 동시에 죽었다.");
			initBattle();
			setBattleState(BattleStateEnum.GAME_OVER);
		}
	}

	private void initBattle() {
		Buff casting = skillAssets.getBuff("casting");
		Buff overwork = skillAssets.getBuff("overwork");
		for (Unit unit : getBattleMemberList()) {

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

	private boolean isDead(Unit defendUnit) {
		return defendUnit.getStatus().getHp() <= 1;
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
		return isDead(selectedMonster);
	}

	public Monster getSelectedMonster() {
		return selectedMonster;
	}

	public void setSelectedMonster(Monster selectedMonster) {
		this.selectedMonster = selectedMonster;
	}

	public BattleStateEnum getBattleState() {
		return this.currentBattleState;
	}

	public void setBattleState(BattleStateEnum battleStateEnum) {
		currentBattleState = battleStateEnum;
		if (currentBattleState.equals(BattleStateEnum.IN_GAME)) {
			musicManager.setMusicAndPlay("bgm_battle");
		}
	}
	public void setCurrentSelectedSkill(Skill skill) {
		this.currentSelectedSkill = skill;
	}

	public Skill getCurrentSelectedSkill() {
		return currentSelectedSkill;
	}

	public boolean isShowGrid() {
		return isShowGrid;
	}

	public void setShowGrid(boolean showGrid) {
		if (showGrid) {
			gridHitbox.showGrid();
		} else {
			gridHitbox.hideGrid();
		}

		this.isShowGrid = showGrid;
	}

	public PriorityQueue<Unit> getOrderedUnits() {
		return orderedUnits;
	}

	public void setOrderedUnits(PriorityQueue<Unit> orderedUnits) {
		this.orderedUnits = orderedUnits;
	}

	public boolean isSmallUpdate() {
		return isSmallUpdate;
	}

	public void setSmallUpdate(boolean isUpdate) {
		this.isSmallUpdate = isUpdate;
	}

	public boolean isBigUpdate() {
		return isBigUpdate;
	}

	public void setBigUpdate(boolean isBigUpdate) {
		this.isBigUpdate = isBigUpdate;
	}

	public int getRunPercent() {
		return runPercent;
	}

	public void setRunPercent(int runPercent) {
		this.runPercent = runPercent;
	}

	public Unit getCurrentAttackUnit() {
		return currentAttackUnit;
	}

	public void setCurrentAttackUnit(Unit currentAttackUnit) {
		this.currentAttackUnit = currentAttackUnit;
	}

	public boolean isSkill() {
		return isSkill;
	}

	public void setSkill(boolean isSkill) {
		this.isSkill = isSkill;
	}

	public GridHitbox getGridHitbox() {
		return gridHitbox;
	}

	public void setMonsterSize(MonsterEnum.SizeType type) {
		gridHitbox.setTextureManager(textureManager);
		gridHitbox.setUiConstantsMap(constantsAssets);
		gridHitbox.setSizeType(type);
	}

	public int getGridLimitNum() {
		return gridHitbox.getLimitNum();
	}

	public void setGridLimitNum(int num) {
		gridHitbox.setLimitNum(num);
	}

	public Item getCurrentSelectedItem() {
		return currentSelectedItem;
	}

	public void setCurrentSelectedItem(Item currentSelectedItem) {
		this.currentSelectedItem = currentSelectedItem;
	}

	public List<Buff> getMonsterBuffList() {
		return getSelectedMonster().getBuffList();
	}

	public String getText() {
		return battleDescriptionLabel;
	}

	public String getBattleDescriptionLabel() {
		return battleDescriptionLabel;
	}

	public void setBattleDescriptionLabel(String text) {
		this.battleDescriptionLabel = text;
	}

	public boolean isEventBattle() {
		return isEventBattle;
	}

	public void setBattleStage(BattleStage stage) {
		battleStage = stage;
	}

	public void setEventBattle(boolean isEventBattle) {
		this.isEventBattle = isEventBattle;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setrMenuButtonList(ArrayList<ImageButton> rMenuButtonList) {
		this.rMenuButtonList = rMenuButtonList;

	}
	public void setEndBuff(boolean isEndBuff) {
		this.isEndBuff = isEndBuff;
	}

	public boolean isEndBuff() {
		return isEndBuff;
	}

	public void getExperience(Monster selectedMonster) {
		for (Unit unit : partyManager.getBattleMemberList()) {
			unit.getStatus().setExperience(
					unit.getStatus().getExperience() + selectedMonster.getStatus().getExperience());
		}
		setBattleDescriptionLabel("경험치 " + selectedMonster.getStatus().getExperience() + "을 얻었다!");
		partyManager.calculateLevel();
	}

	public void getDropItem(Monster selectedMonster) {
		Random random = new Random();
		if (selectedMonster.getDropItems().size() != 0) {
			Item item = selectedMonster.getDropItems().get(random.nextInt(selectedMonster.getDropItems().size()));
			Gdx.app.log("BattleManager", "아이템 [" + item.getName() + "]를 획득하였다.");
			switch (item.getItemType()) {
				case ACCESSORY :
				case CLOTHES :
				case HANDGRIP :
					bagManager.getEquipmentList().add((Equipment) item);
					break;
				case CONSUMABLES :
					bagManager.getConsumablesList().add((Consumables) item);
					break;
				case ETC_ITEM :
					bagManager.getEtcItemList().add(item);
					break;
				default :
					Gdx.app.log("BattleManager", "itemType정보 오류 - " + item.getItemType());
					break;
			}
			setGetItem(true);
			setDropItem(item);
		}
	}

	public void battleCommandAction() {
		getRMenuTable().addAction(Actions.moveTo(1720, 15));
		getRMenuTable().addAction(Actions.moveTo(1720, 15, 1));
	}
	public BattleCommandEnum getCurrentUsingCommand() {
		return currentUsingCommand;
	}

	public void setCurrentUsingCommand(BattleCommandEnum currentUsingCommand) {
		this.currentUsingCommand = currentUsingCommand;
	}

	public String getBattleInfoMessage() {
		return battleInfoMessage;
	}

	public void setBattleInfoMessage(String battleInfoMessage) {
		this.battleInfoMessage = battleInfoMessage;
	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}

	public Table getRMenuTable() {
		return rMenuTable;
	}

	public void setRMenuTable(Table rMenuTable) {
		this.rMenuTable = rMenuTable;
	}

	public boolean isGetItem() {
		return isGetItem;
	}

	public void setGetItem(boolean isGetItem) {
		this.isGetItem = isGetItem;
	}

	public Item getDropItem() {
		return dropItem;
	}

	public void setDropItem(Item dropItem) {
		this.dropItem = dropItem;
	}

	public ArrayList<ImageButton> getRMenuButtonList() {
		return rMenuButtonList;
	}

	public void initializeProperties() {
		setGridHitbox(new GridHitbox());
		setSkill(false);
	}

	public void initializeBattleMember(List<Hero> battleMemberHeroList, Monster monster) {
		List<Unit> battleMemberList = new ArrayList<>();
		battleMemberList.addAll(battleMemberHeroList);
		battleMemberList.add(monster);
		setBattleMemberList(battleMemberList);
		PriorityQueue<Unit> orderedQueue = new PriorityQueue<Unit>();
		orderedQueue.addAll(battleMemberHeroList);
		setOrderedUnits(orderedQueue);
		setBattleDescriptionLabel(monster.getName() + BattleMessages.MEET_MESSAGE);

		for (Unit hero : battleMemberList) {
			hero.setGauge(100);
			hero.setAggro(100);
			hero.setRealAggro(100);
			hero.setSubValue(0);
			hero.setOverload(0);
			hero.setActingPower(-24000 / (hero.getStatus().getSpeed() + 300) + 160);
			hero.setRealStatus(hero.getStatus());
		}
		monster.getStatus().setHp(monster.getStatus().getMaxHp());
		monster.setRealStatus(monster.getStatus());
		setCurrentUsingCommand(BattleCommandEnum.NO_COMMAND);
		setBattleState(BattleStateEnum.IN_GAME);
	}

	public void playBattleAction(float delta) {
		hideBattleCommandButtons();
		animationManager.nextFrame(delta);
		if (animationManager.isEmptyAnimation()) {
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_COMMAND, "normal_attack");
			if (getCurrentSelectedSkill() != null) {
				storySectionManager.triggerNextSectionEvent(EventTypeEnum.BATTLE_COMMAND, "skill_attack");
			}
			endTurn();
			setCurrentSelectedSkill(null);
			if (getBattleState().equals(BattleStateEnum.PLAYER_WIN)) {
				questManager.checkHuntQuest(selectedMonster.getFacePath());
				setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				musicManager.stopMusic();
				soundManager.setSoundByPathAndPlay("notice_victory");
				getDropItem(selectedMonster);
				getExperience(selectedMonster);
				showBattleInfoMessage(BattleMessages.PLAYER_WIN_MESSAGE.toString());

			} else if (getBattleState().equals(BattleStateEnum.GAME_OVER)) {
				screenFactory.show(ScreenEnum.GAME_OVER);
			}
		}
	}

	public void setGridHitbox(GridHitbox gridHitbox) {
		this.gridHitbox = gridHitbox;
	}

	public List<Unit> getBattleMemberList() {
		return battleMemberList;
	}

	public void setBattleMemberList(List<Unit> battleMemberList) {
		this.battleMemberList = battleMemberList;
	}

	public void showBattleInfoMessage(String battleInfoMessage) {
		setBattleInfoMessage(battleInfoMessage);
		BattleScreen.showBattleInfoMessage = true;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}
}
