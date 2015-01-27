package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.battle.Battle;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.model.LivingUnit;
import com.mygdx.model.Monster;

@Component
public class BattleManager {
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PartyInfo partyInfo;
	private Battle battle = new Battle();
	private Monster monster;

	public void userAttack(LivingUnit unit) {
		monster = movingInfo.getSelectedMonster();
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
		battle.attack(monster, partyInfo.pickRandomHero());
	}

	public void nextTurn() {
		// TODO
	}

}
