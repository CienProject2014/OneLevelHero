package com.mygdx.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.BattleInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.SkillTargetEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.item.Consumables;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;
import com.mygdx.ui.GridHitbox;

public class BattleManager {
	private final String TAG = "BattleManager";
	private final int TIME_FLOW_RATE = 1;

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
	private BattleInfo battleInfo;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private SoundManager soundManager;
	private GridHitbox gridHitbox; // grid hitbox 테이블

	public SkillRunPopup gameObjectPopup;

	public boolean isEventBattle() {
		return battleInfo.isEventBattle();
	}

	public void setEventBattle(boolean isEventBattle) {
		battleInfo.setEventBattle(isEventBattle);
	}

	public void setBackgroundPath(String backgroundPath) {
		battleInfo.setBackgroundPath(backgroundPath);
	}

	public String getBackgroundPath() {
		return battleInfo.getBackgroundPath();
	}

	public void startBattle(Monster selectedMonster) {
		if (battleInfo.getBattleState().equals(BattleStateEnum.NOT_IN_BATTLE)) {
			battleInfo.setBattleState(BattleStateEnum.ENCOUNTER);
		}
		unitManager.initiateMonster(selectedMonster);
		battleInfo.setCurrentMonster(selectedMonster);
		if (fieldManager.isInField()) {
			screenFactory.show(ScreenEnum.BATTLE);
		} else if (dungeonManager.isInDungeon()) {
			screenFactory.show(ScreenEnum.BATTLE);
		}
	}

	public Unit getCurrentActors() {
		Unit currentAttackUnit = getOrderedUnits().poll();
		if (currentAttackUnit instanceof Hero) {
			setCurrentActor((Hero) currentAttackUnit);
		}
		return currentAttackUnit;
	}

	public void checkCurrentState() {
		switch (getCurrentClickStateEnum()) {
			case NORMAL :
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL1 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL2 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL3 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL4 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL5 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL6 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case SKILL7 :
				battleInfo.setSkill(false);
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case ITEM :
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case DEFENSE :
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case WAIT :
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			case RUN :
				setShowGrid(false);
				battleInfo.getCurrentActor().setGauge(battleInfo.getCurrentActor().getPreGague());
				timeManager.plusSecond(-timeManager.getPreTime());
				break;
			default :
				break;
		}
	}

	public void endTurn() {
		updateOrder();
		battleInfo.setCurrentAttackUnit(getCurrentActors());
		setBigUpdate(true);
		setSmallUpdate(true);
		showRMenuButtons();
		setCurrentClickStateEnum(CurrentClickStateEnum.DEFAULT);
	}

	public void runAway() {
		int nGetVal = (int) Math.round(Math.random() * 100);

		if (battleInfo.getRunPercent() > nGetVal) {
			battleInfo.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
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
			soundManager.setSoundByPathAndPlay("slash");
			readyForPlayerAnimation("empty hit", centerX, 4 - centerY, (int) (StaticAssets.windowHeight * 0.5f),
					(int) (StaticAssets.windowHeight * 0.5f));
		} else {
			soundManager.setSoundByPathAndPlay("attack_cutting");
			readyForMonsterAnimation((Hero) defendUnit, "empty hit", (int) (StaticAssets.windowHeight * 0.3f),
					(int) (StaticAssets.windowHeight * 0.3f));
		}
		checkIsBattleEnd();
	}

	public void afterClick(int useGague) {
		calCostGague(getCurrentAttackUnit(), useGague);
		updateOrder();
		setSmallUpdate(true);
	}

	public void updateOrder() {
		// Turn Logic
		Collections.sort(getUnits());
		setOrderedUnits(new LinkedList<Unit>(getUnits()));
	}

	public void useSkill(Unit attackUnit, Unit targetUnit, String skillName) {
		Skill skill = skillAssets.getSkill(skillName);
		ArrayList<Unit> targetList = getTargetList(skill.getSkillTargetType(), attackUnit, targetUnit);
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
		unit.setGauge(unit.getGauge() - costGague);
		timeManager.setPreTime(costGague * TIME_FLOW_RATE);
		timeManager.plusSecond(costGague * TIME_FLOW_RATE);

		healGague();

		for (Unit buffUnit : getUnits()) {
			buffUnit.getBattleStrategy().runBuffEffect(buffUnit);
		}
	}

	public void FuckingCostGague(Unit unit, int typeOfAction) {
		unit.setPreGague(unit.getGauge());
		unit.setGauge(unit.getGauge() - typeOfAction);
		timeManager.setPreTime(typeOfAction * TIME_FLOW_RATE);
		timeManager.plusSecond(typeOfAction * TIME_FLOW_RATE);
		healGague();
	}

	public void useItem() {
		if (getCurrentSelectedItem() instanceof Consumables) {
			Consumables potion = (Consumables) getCurrentSelectedItem();
			Unit unit = battleInfo.getCurrentAttackUnit();
			if ((unit.getStatus().getHp() + potion.getHeal() < unit.getStatus().getMaxHp())) {
				unit.getStatus().setHp(unit.getStatus().getHp() + potion.getHeal());
			} else {
				unit.getStatus().setHp(unit.getStatus().getMaxHp());
			}
		}
	}

	public void waitButton() {
		int maxGague = 0;
		int maxSubValue = 0;
		for (Unit unit : partyManager.getBattleMemberList()) {
			if (battleInfo.getCurrentAttackUnit() == unit) {
			} else {
				if (maxGague <= unit.getGauge()) {
					maxGague = unit.getGauge();
				}
			}
		}

		if ((100 - maxGague) == 0) {
			battleInfo.getCurrentAttackUnit().setSubvalue(battleInfo.getCurrentAttackUnit().getSubvalue() + 1);
		} else {
			battleInfo.getCurrentAttackUnit().setSubvalue(1);
		}

		FuckingCostGague(battleInfo.getCurrentAttackUnit(), 100 - maxGague);

		for (Unit unit : partyManager.getBattleMemberList()) {
			if (battleInfo.getCurrentAttackUnit() == unit) {
			} else {
				if (maxSubValue <= unit.getSubvalue()) {
					maxSubValue = unit.getSubvalue();
				}
			}
		}
		battleInfo.getCurrentAttackUnit().setSubvalue(maxSubValue + 1);
		if (battleInfo.getCurrentAttackUnit().getSubvalue() == 3) {
			FuckingCostGague(battleInfo.getCurrentAttackUnit(), 1);
			battleInfo.getCurrentAttackUnit().setSubvalue(0);
		}
	}

	private void healGague() {
		int maxGague = 0;
		for (Unit unit : battleInfo.getUnits()) {
			if (maxGague <= unit.getGauge()) {
				maxGague = unit.getGauge();
			}
		}

		for (Unit unit : battleInfo.getUnits()) {
			unit.setGauge(unit.getGauge() + 100 - maxGague);
		}
	}

	public void hideRMenuButtons() {
		for (ImageButton rMenuButton : battleInfo.getrMenuButtonList()) {
			rMenuButton.setVisible(false);
			rMenuButton.setTouchable(Touchable.disabled);
		}
	}

	public void showRMenuButtons() {
		for (ImageButton rMenuButton : battleInfo.getrMenuButtonList()) {
			rMenuButton.setVisible(true);
			rMenuButton.setTouchable(Touchable.enabled);
		}
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
		int x = (int) (StaticAssets.windowWidth / 2);
		int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(animationName, x, y, width, height);
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
		battleInfo.setCurrentActor(hero);
	}

	public Hero getCurrentActor() {
		return battleInfo.getCurrentActor();
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
		for (Unit unit : battleInfo.getUnits()) {
			unit.setGauge(100);
			unit.setSubvalue(0);
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
		return isDead(battleInfo.getCurrentMonster());
	}

	public void useItem(String item) {
	}

	public Monster getSelectedMonster() {
		return battleInfo.getCurrentMonster();
	}

	public void setSelectedMonster(Monster selectedMonster) {
		battleInfo.setCurrentMonster(selectedMonster);
	}

	public BattleStateEnum getBattleState() {
		return battleInfo.getBattleState();
	}

	public void setBattleState(BattleStateEnum battleStateEnum) {
		battleInfo.setBattleState(battleStateEnum);
		if (battleStateEnum.equals(BattleStateEnum.IN_GAME)) {
			musicManager.setMusicAndPlay("fights");
		} else if (battleStateEnum.equals(BattleStateEnum.GAME_OVER)
				|| battleStateEnum.equals(BattleStateEnum.NOT_IN_BATTLE)) {
		}
	}

	public CurrentClickStateEnum getCurrentClickStateEnum() {
		return battleInfo.getcurrentClickStateEnum();
	}

	public void setCurrentClickStateEnum(CurrentClickStateEnum currentClickState) {
		battleInfo.setCurrentClickStateEnum(currentClickState);
	}

	public void setBeforePosition(PositionEnum positionEnum) {
		battleInfo.setBeforePosition(positionEnum);
	}

	public PositionEnum getBeforePosition() {
		return battleInfo.getBeforePosition();
	}

	public void setCurrentSelectedSkill(Skill skill) {
		battleInfo.setCurrentSelectedSkill(skill);
	}

	public Skill getCurrentSelectedSkill() {
		return battleInfo.getCurrentSelectedSkill();
	}

	public boolean isShowGrid() {
		return battleInfo.isShowGrid();
	}

	public void setShowGrid(boolean showGrid) {
		if (showGrid) {
			gridHitbox.showGrid();
		} else {
			gridHitbox.hideGrid();
		}

		battleInfo.setShowGrid(showGrid);
	}

	public void setUnits(ArrayList<Unit> units) {
		battleInfo.setUnits(units);
	}

	public ArrayList<Unit> getUnits() {
		return battleInfo.getUnits();
	}

	public Queue<Unit> getOrderedUnits() {
		return battleInfo.getOrderedUnits();
	}

	public void setOrderedUnits(Queue<Unit> orderedUnits) {
		battleInfo.setOrderedUnits(orderedUnits);
	}

	public ArrayList<ImageButton> getrMenuButtonList() {
		return battleInfo.getrMenuButtonList();
	}

	public void setrMenuButtonList(ArrayList<ImageButton> rMenuButtonList) {
		battleInfo.setrMenuButtonList(rMenuButtonList);
	}

	public boolean isSmallUpdate() {
		return battleInfo.isSmallUpdate();
	}

	public void setSmallUpdate(boolean isUpdate) {
		battleInfo.setSmallUpdate(isUpdate);
	}

	public boolean isBigUpdate() {
		return battleInfo.isBigUpdate();
	}

	public void setBigUpdate(boolean isBigUpdate) {
		battleInfo.setBigUpdate(isBigUpdate);
	}

	public int getRunPercent() {
		return battleInfo.getRunPercent();
	}

	public void setRunPercent(int runPercent) {
		battleInfo.setRunPercent(runPercent);
	}

	public Unit getCurrentAttackUnit() {
		return battleInfo.getCurrentAttackUnit();
	}

	public void setCurrentAttackUnit(Unit currentAttackUnit) {
		battleInfo.setCurrentAttackUnit(currentAttackUnit);
	}

	public boolean isSkill() {
		return battleInfo.isSkill();
	}

	public void setSkill(boolean isSkill) {
		battleInfo.setSkill(isSkill);
	}

	public GridHitbox getGridHitbox() {
		return gridHitbox = new GridHitbox();
	}

	public GridHitbox getNowGridHitbox() {
		return gridHitbox;
	}

	public void setGridHitbox(GridHitbox gridHitbox) {
		this.gridHitbox = gridHitbox;
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
		return battleInfo.getCurrentSelectedItem();
	}

	public void setCurrentSelectedItem(Item currentSelectedItem) {
		battleInfo.setCurrentSelectedItem(currentSelectedItem);
	}

	public List<Buff> getMonsterBuffList() {
		return getSelectedMonster().getBuffList();
	}

}
