package com.mygdx.manager;

import com.mygdx.battle.Battle;
import com.mygdx.model.LivingUnit;
import com.mygdx.model.Monster;
import com.mygdx.state.CurrentState;

public class BattleManager {
	private Battle battle = new Battle();
	private Monster monster = CurrentState.getInstance().getCurrentPosition()
			.getCurrentMovingInfo().getSelectedMonster();

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
