package com.mygdx.model;

public class Hero extends LivingUnit implements Fightable {
	private Inventory inventory;
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
