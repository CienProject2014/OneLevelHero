package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.unit.Hero;

public class PartyInfo {
	private List<Hero> partyList = new ArrayList<Hero>();
	private List<Hero> battleMemberList = new ArrayList<Hero>();
	private Hero currentSelectedHero;
	private int Fatigue;
	private int Level;

	public List<Hero> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<Hero> partyList) {
		this.partyList = partyList;
	}

	public List<Hero> getBattleMemberList() {
		return battleMemberList;
	}

	public void setBattleMemberList(List<Hero> battleMemberList) {
		this.battleMemberList = battleMemberList;
	}

	public Hero getCurrentSelectedHero() {
		return currentSelectedHero;
	}

	public void setCurrentSelectedHero(Hero currentSelectedHero) {
		this.currentSelectedHero = currentSelectedHero;
	}

	public int getFatigue() {
		return Fatigue;
	}

	public void setFatigue(int fatigue) {
		Fatigue = fatigue;
	}

	public int getLevel() {
		return Level;
	}

}
