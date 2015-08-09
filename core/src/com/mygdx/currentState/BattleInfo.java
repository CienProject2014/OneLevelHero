package com.mygdx.currentState;

import com.mygdx.enums.BattleStateEnum;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;

public class BattleInfo {
	private Monster currentMonster;
	private BattleStateEnum battleState;
	private Hero currentActor;

	public Hero getCurrentActor() {
		return currentActor;
	}

	public void setCurrentActor(Hero currentActor) {
		this.currentActor = currentActor;
	}

	public BattleStateEnum getBattleState() {
		return battleState;
	}

	public void setBattleState(BattleStateEnum battleState) {
		this.battleState = battleState;
	}

	public Monster getCurrentMonster() {
		return currentMonster;
	}

	public void setCurrentMonster(Monster currentMonster) {
		this.currentMonster = currentMonster;
	}

}
