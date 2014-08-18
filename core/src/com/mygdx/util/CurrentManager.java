package com.mygdx.util;

import com.mygdx.resource.SaveVersion;
import com.mygdx.unit.Unit;

public class CurrentManager {
	private SaveVersion saveVersion;
	private String village;
	private Unit unit;

	public CurrentManager() {

	}

	public SaveVersion getVersion() {
		return saveVersion;
	}

	public void setVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
