package com.mygdx.util;

import com.mygdx.resource.SaveVersion;
import com.mygdx.unit.Unit;

public class CurrentManager {
	private SaveVersion saveVersion;
	private String village;
	private Unit unit;

	private String currentPosition = "O";
	private String currentState = "village";
	private String currentStarting;
	private String currentDestination;
	private String currentDungeon;

	private static CurrentManager instance;

	public static CurrentManager getInstance() {
		if (null == instance) {
			instance = new CurrentManager();
		}
		return instance;
	}

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

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getCurrentStarting() {
		return currentStarting;
	}

	public void setCurrentStarting(String currentStarting) {
		this.currentStarting = currentStarting;
	}

	public String getCurrentDestination() {
		return currentDestination;
	}

	public void setCurrentDestination(String currentDestination) {
		this.currentDestination = currentDestination;
	}
	
	public String getCurrentDungeon() {
		return currentDungeon;
	}

	public void setCurrentDungeon(String currentDungeon) {
		this.currentDungeon = currentDungeon;
	}
}
