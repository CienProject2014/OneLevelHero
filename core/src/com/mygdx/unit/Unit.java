package com.mygdx.unit;

public class Unit {
	public Status status;
	public Bag bag;
	private String unitName;

	public Unit(String unitName, Status status, Bag bag) {
		this.unitName = unitName;
		this.status = status;
		this.bag = bag;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
