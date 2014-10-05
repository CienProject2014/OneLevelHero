package com.mygdx.util;

import java.io.Serializable;

import com.mygdx.resource.SaveVersion;
import com.mygdx.unit.Hero;
import com.mygdx.unit.Party;

public class CurrentManager implements Serializable {
	private SaveVersion saveVersion;
	private String village;
	//<String, Enum>타입의 HashMap으로 바꾸기
	private Hero leftHero;
	private Hero centerHero;
	private Hero rightHero;
	public Party party;

	private String currentPosition = "O";
	private String currentState = "village";
	private String currentStarting;
	private String currentDestination;
	private static CurrentManager instance;

	public static CurrentManager getInstance() {
		if (null == instance) {
			instance = new CurrentManager();
		}
		return instance;
	}

	public CurrentManager() {
		party = new Party();
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

	public SaveVersion getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}

	public Hero getLeftHero() {
		return leftHero;
	}

	public void setLeftHero(Hero leftHero) {
		this.leftHero = leftHero;
	}

	public Hero getCenterHero() {
		return centerHero;
	}

	public void setCenterHero(Hero centerHero) {
		this.centerHero = centerHero;
	}

	public Hero getRightHero() {
		return rightHero;
	}

	public void setRightHero(Hero rightHero) {
		this.rightHero = rightHero;
	}

	public static void setInstance(CurrentManager instance) {
		CurrentManager.instance = instance;
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

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
}
