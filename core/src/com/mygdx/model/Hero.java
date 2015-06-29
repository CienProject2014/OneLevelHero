package com.mygdx.model;

public class Hero extends LivingUnit implements Fightable {
	private Equipment equipment;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
}
