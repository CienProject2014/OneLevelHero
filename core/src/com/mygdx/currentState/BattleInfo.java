package com.mygdx.currentState;

import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;

public class BattleInfo {
	private Monster monster;
	private BattleStateEnum battleState;
	private CurrentClickStateEnum currentClickState;
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

	public CurrentClickStateEnum getcurrentClickStateEnum() {
		return currentClickState;
	}

	public void setCurrentClickStateEnum(
			CurrentClickStateEnum currentClickState) {
		this.currentClickState = currentClickState;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}
}
