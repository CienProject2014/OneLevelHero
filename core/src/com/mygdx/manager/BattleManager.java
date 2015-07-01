package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.battle.Battle;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.enums.TextureEnum;
import com.mygdx.model.LivingUnit;
import com.mygdx.model.Monster;

public class BattleManager {
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PartyInfo partyInfo;
	
	private AnimationManager animationManager;

	private Battle battle = new Battle();
	private Monster monster;

	public void userAttack(LivingUnit unit) {
		monster = movingInfo.getSelectedMonster();
		// FIXME
		battle.attack(unit, monster);
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING2, 544, 264);
	}

	public void userSkill(LivingUnit unit, String skill) {
		// FIXME
		battle.skillAttack(unit, skill);
	}

	public void useItem(String item) {
		// TODO
	}

	public void monsterAttack() {
		// FIXME
		battle.attack(monster, partyInfo.pickRandomHero());
		animationManager.registerAnimation(TextureEnum.ATTACK_CUTTING, 544, 80);
	}

	public void nextTurn() {
		// TODO
	}
	
	public MovingInfo getMovingInfo() {
		return movingInfo;
	}

	public void setMovingInfo(MovingInfo movingInfo) {
		this.movingInfo = movingInfo;
	}

	public PartyInfo getPartyInfo() {
		return partyInfo;
	}

	public void setPartyInfo(PartyInfo partyInfo) {
		this.partyInfo = partyInfo;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}

}
