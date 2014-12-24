package com.mygdx.model;

public class VillageInfo {
	private String village;
	private String currentPosition = "O";
	private String currentState = "village";
	private String currentStarting;
	private String currentDestination;
	private String currentDungeon;

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
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
