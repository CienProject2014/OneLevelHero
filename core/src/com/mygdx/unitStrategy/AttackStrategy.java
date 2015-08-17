package com.mygdx.unitStrategy;

import com.mygdx.model.unit.Unit;

public interface AttackStrategy {
	public void attack(Unit attacker, Unit defender);
	public void skillAttack(Unit attacker, Unit defender, String skillName);
}
