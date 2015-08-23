package com.mygdx.manager;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.SkillAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.BattleInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.SkillTargetEnum;
import com.mygdx.enums.TextureEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class BattleManager {
	private final String TAG = "BattleManager";

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
	private FieldManager fieldManager;

	public boolean isEventBattle() {
		return battleInfo.isEventBattle();
	}

	public void setEventBattle(boolean isEventBattle) {
		battleInfo.setEventBattle(isEventBattle);
	}

	public void startBattle(Monster selectedMonster) {
		if (battleInfo.getBattleState().equals(BattleStateEnum.NOT_IN_BATTLE)) {
			battleInfo.setBattleState(BattleStateEnum.ENCOUNTER);
		}
		unitManager.initiateMonster(selectedMonster);
		battleInfo.setCurrentMonster(selectedMonster);
		if (fieldManager.isInField()) {
			screenFactory.show(ScreenEnum.ENCOUNTER);
		}
	}

	public void runAway() {
		battleInfo.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
		movingManager.goCurrentPosition();
	}

	public void attack(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		attackUnit.attack(defendUnit, hitArea);
		if (attackUnit instanceof Hero) {
			// TODO empty animation
			readyForPlayerAnimation("empty hit");
		} else {
			// TODO empty animation

			readyForMonsterAnimation("empty hit");
		}
		checkIsBattleEnd();
	}

	public void useSkill(Unit attackUnit, Unit targetUnit, String skillName) {

		Skill skill = skillAssets.getSkill(skillName);

		ArrayList<Unit> targetList = getTargetList(skill.getSkillTargetType(), attackUnit, targetUnit);

		attackUnit.useSkill(targetList, skill);

		// SkillTargetType에 따라 타겟 설정
		if (attackUnit instanceof Hero) {
			// TODO empty animation
			readyForPlayerAnimation("empty");
		} else {
			// TODO empty animation
			readyForMonsterAnimation("empty");
		}

		checkIsBattleEnd();
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

	public void readyForMonsterAnimation(String animationName) {
		final int x = (int) (StaticAssets.windowWidth / 8);
		final int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING, x, y);
	}

	public void readyForPlayerAnimation(String animationName) {
		int x = (int) (StaticAssets.windowWidth / 2);
		int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING2, x, y);
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

	private void checkIsBattleEnd() {
		boolean monsterState = isMonsterDead();
		boolean partyState = isHeroDead();
		if (monsterState && !partyState) {
			Gdx.app.log(TAG, "용사팀의 승리!");
			setBattleState(BattleStateEnum.PLAYER_WIN);
		} else if (partyState && !monsterState) {
			Gdx.app.log(TAG, "용사팀의 패배!");
			setBattleState(BattleStateEnum.GAME_OVER);
		} else if (partyState && monsterState) {
			Gdx.app.log(TAG, "잘못된 배틀 : 동시에 죽었다.");
			setBattleState(BattleStateEnum.GAME_OVER);
		}
	}

	private boolean isDead(Unit defendUnit) {
		return defendUnit.getStatus().getHp() <= 0;
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
		// TODO
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

}
