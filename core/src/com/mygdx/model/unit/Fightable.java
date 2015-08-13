package com.mygdx.model.unit;

public interface Fightable {
	public void attack(Unit defender);

	public void skillAttack(Unit defender, String skillName);

}
