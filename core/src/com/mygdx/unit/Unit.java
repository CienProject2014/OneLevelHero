package com.mygdx.unit;

import com.badlogic.gdx.Gdx;

public class Unit {
	public Status status;
	public Bag bag;
	private String unitName;

	public Unit(String unitName, Status status, Bag bag) {
		this.unitName = unitName;
		this.status = status;
		this.bag = bag;
		Gdx.app.log("DEBUG", "unit is constructed");
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
