package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.BattleInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.TextureEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.Fightable;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.Unit;

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

	public void startBattle(Monster selectedMonster) {
		battleInfo.setMonster(selectedMonster);
		battleInfo.setBattleState(BattleStateEnum.ING);
		screenFactory.show(ScreenEnum.ENCOUNTER);
	}

	public void runAway() {
		goCurrentPosition();
	}

	public void readyMonsterHitAnimation() {
		final int x = (int) (StaticAssets.windowWidth / 8);
		final int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING, x, y);
	}

	public void readyPlayerHitAnimation() {
		int x = (int) (StaticAssets.windowWidth / 2);
		int y = (int) (StaticAssets.windowHeight / 2);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING2, x, y);
	}

	private void goCurrentPosition() {
		movingManager.goCurrentPosition();
	}

	public void setCurrentActor(Hero hero) {
		battleInfo.setCurrentActor(hero);
	}

	public Hero getCurrentActior() {
		return battleInfo.getCurrentActor();
	}

	public void endBattle(Unit loseUnit) {
		if (loseUnit instanceof Monster) {
			battleInfo.setBattleState(BattleStateEnum.WIN);
			Gdx.app.log("BattleManager", "용사팀의 승리!");
		} else {
			battleInfo.setBattleState(BattleStateEnum.LOSE);
			Gdx.app.log("BattleManager", "용사팀의 패배!");
		}
		//FIXME : 게임 종료를 알리는 장치 필요
		goCurrentPosition();
	}

	public void attack(Unit attackUnit, Unit defendUnit) {
		// FIXME
		attackUnit.attack(defendUnit);
		readyHitAnimation(attackUnit);
		checkIsDead(defendUnit);
	}

	public void readyHitAnimation(Unit attackUnit) {
		if (attackUnit instanceof Hero) {
			readyPlayerHitAnimation();
		} else {
			readyMonsterHitAnimation();
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
			Gdx.app.log("BattleManager", "용사팀의 패배..!");
		}
	}

	public Monster getSelectedMonster() {
		return battleInfo.getMonster();
	}

	public void setSelectedMonster(Monster selectedMonster) {
		battleInfo.setMonster(selectedMonster);
	}
}
