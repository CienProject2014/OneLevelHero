package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.Hero;

public class PartyInfo {
	private List<Hero> partyList = new ArrayList<Hero>();
	private List<Hero> battleMemberList = new ArrayList<Hero>();
	private int selectedIndex = 0;

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

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
}
