package com.mygdx.manager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.battle.Battle;
import com.mygdx.currentState.CurrentState;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.model.LivingUnit;
import com.mygdx.model.Monster;

@Component
public class BattleManager {
	@Autowired
	private MovingInfo movingInfo;
	private Battle battle = new Battle();
	private Monster monster;

	@PostConstruct
	public void init() {
		monster = movingInfo.getSelectedMonster();
	}

	public void userAttack(LivingUnit unit) {
		// FIXME
		battle.attack(unit, monster);

		monsterAction();
	}

	public void userSkill(LivingUnit unit, String skill) {
		// FIXME
		battle.skillAttack(unit, skill);
		monsterAction();
	}

	public void useItem(String item) {
		// TODO
	}

	public void monsterAction() {
		// FIXME
		battle.attack(monster, CurrentState.getInstance().getParty()
				.pickRandomHero());
	}

	public void nextTurn() {
		// TODO
	}

}
