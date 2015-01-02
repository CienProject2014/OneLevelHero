package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.mygdx.battle.Battle;
import com.mygdx.model.LivingUnit;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.state.CurrentState;

public class BattleManager {
	private static Battle battle;
	
	public BattleManager() {
		battle = new Battle();
	}
	
	public void userAttack(LivingUnit unit) {
		// FIXME
		battle.attack(unit, CurrentState.getInstance().getCurrentDungeon().getMonster());
		
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
		battle.attack(CurrentState.getInstance().getCurrentDungeon().getMonster(), 
				CurrentState.getInstance().getParty().pickRandomHero());
	}
	
	public void nextTurn() {
		// TODO
	}
	
}
