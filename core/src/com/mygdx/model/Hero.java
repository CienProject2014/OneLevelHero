package com.mygdx.model;

public class Hero extends LivingUnit implements Fightable {
	private Inventory inventory;

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
