package com.mygdx.manager;

import java.io.Serializable;
import java.util.List;

import com.mygdx.model.Hero;
import com.mygdx.model.NPC;
import com.mygdx.model.Party;
import com.mygdx.resource.Assets;
import com.mygdx.resource.SaveVersion;

public class CurrentManager implements Serializable {
	private SaveVersion saveVersion;
	private String village;
	//<String, Enum>타입의 HashMap으로 바꾸기
	public List<Hero> heroes;
	public Party party;
	private NPC currentNPC;
	private int currentEventNumber;

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

	public NPC getCurrentNPC() {
		return currentNPC;
	}

	public void setCurrentNPC(String currentNpcName) {
		this.currentNPC = Assets.npcMap.get(currentNpcName);
	}

	public int getCurrentEventNumber() {
		return currentEventNumber;
	}

	public void setCurrentEventNumber(int currentEventNumber) {
		this.currentEventNumber = currentEventNumber;
	}

	public List<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<Hero> heroes) {
		this.heroes = heroes;
	}
}
