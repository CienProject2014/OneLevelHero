package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.BattleInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.TextureEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.unit.Fightable;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class BattleManager {
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private BattleInfo battleInfo;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private AnimationManager animationManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;

	public void startBattle(Monster selectedMonster) {
		if (battleInfo.getBattleState().equals(BattleStateEnum.NOT_IN_BATTLE)) {
			battleInfo.setBattleState(BattleStateEnum.ENCOUNTER);
		}
		battleInfo.setMonster(selectedMonster);
		screenFactory.show(ScreenEnum.ENCOUNTER);
	}

	public void runAway() {
		battleInfo.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
		goCurrentPosition();
	}

	public boolean isInBattle() {
		if (getBattleState().equals(BattleStateEnum.NOT_IN_BATTLE)) {
			return false;
		} else {
			return true;
		}
	}

	public void readyForMonsterHittingAnimation() {
		final int x = (int) (StaticAssets.windowWidth / 8);
		final int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING, x, y);
	}

	public void readyForPlayerHittingAnimation() {
		int x = (int) (StaticAssets.windowWidth / 2);
		int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING2, x, y);
	}

	public void goCurrentPosition() {
		movingManager.goCurrentPosition();
	}

	public void setCurrentActor(Hero hero) {
		battleInfo.setCurrentActor(hero);
	}

	public Hero getCurrentActor() {
		return battleInfo.getCurrentActor();
	}

	public void endBattle(Unit loseUnit) {
		if (loseUnit instanceof Monster) {
			Gdx.app.log("BattleManager", "용사팀의 승리!");
		} else {
			Gdx.app.log("BattleManager", "용사팀의 패배!");
		}
		setBattleState(BattleStateEnum.GAME_OVER);
	}

	public void attack(Unit attackUnit, Unit defendUnit) {
		// FIXME
		attackUnit.attack(defendUnit);
		readyHitAnimation(attackUnit);
		checkIsDead(defendUnit);
	}

	public void readyHitAnimation(Unit attackUnit) {
		if (attackUnit instanceof Hero) {
			readyForPlayerHittingAnimation();
		} else {
			readyForMonsterHittingAnimation();
		}
	}

	private void checkIsDead(Unit defendUnit) {
		if (defendUnit.getStatus().getHp() <= 0) {
			endBattle(defendUnit);
		}
	}

	public void userSkill(Fightable attackUnit, String skill) {
		// FIXME
		attackUnit.skillAttack(battleInfo.getMonster(), skill);
	}

	public void useItem(String item) {
		// TODO
	}

	public void checkMonsterWin(Hero randomHero) {
		if (randomHero.getStatus().getHp() <= 0) {
			endBattle(battleInfo.getMonster());
		}
	}

	public Monster getSelectedMonster() {
		return battleInfo.getMonster();
	}

	public void setSelectedMonster(Monster selectedMonster) {
		battleInfo.setMonster(selectedMonster);
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

	public void setCurrentClickStateEnum(
			CurrentClickStateEnum currentClickState) {
		battleInfo.setCurrentClickStateEnum(currentClickState);
	}

	public void healAllHero() {
		for (Hero hero : partyManager.getBattleMemberList()) {
			hero.getStatus()
					.setHealthPoint(hero.getStatus().getMaxHealthPoint());
		}
	}
}
