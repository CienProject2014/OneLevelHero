package com.mygdx.unitStrategy;

import java.util.ArrayList;

import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Unit;

public interface BattleStrategy {
	public void attack(Unit attacker, Unit defender, int[][] hitArea);

	public void useSkill(Unit attacker, ArrayList<Unit> targetList, Skill skill);

	public void runBuffEffect(Unit defender);
}
