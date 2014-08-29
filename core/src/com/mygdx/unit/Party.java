package com.mygdx.unit;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class Party {
	ArrayList<Hero> partyList = new ArrayList<Hero>();
	ArrayList<Hero> battleMemberList = new ArrayList<Hero>();
	final int BATTLEMEMBERLIST = 3;

	public void addParty(Hero hero) {
		partyList.add(hero);
		Gdx.app.log("Info", hero.getUnitName() + "를 파티에 추가했습니다.");
		if (battleMemberList.size() < 3) {
			battleMemberList.add(hero);
			Gdx.app.log("Info", hero.getUnitName() + "를 전투 멤버에 추가했습니다.");
		} else {
			//배틀멤버리스트가 꽉찼다면 배틀멤버리스트에 추가할건지 물어본다. 추후 구현
		}
	}

	public void removeParty(Hero hero) {
		if (battleMemberList.contains(hero))
			battleMemberList.remove(hero);
		partyList.remove(hero);
		Gdx.app.log("Info", hero.getUnitName() + "를 파티에서 열외했습니다.");
	}

	public void removeBattleMember(Hero hero) {
		battleMemberList.remove(hero);
		Gdx.app.log("Info", hero.getUnitName() + "를 전투 멤버에서 열외했습니다.");
	}

	public void addBattleMember(Hero hero) {
		if (partyList.contains(hero)) {
			battleMemberList.add(hero);
			Gdx.app.log("Info", hero.getUnitName() + "를 전투 멤버에 추가했습니다.");
		} else {
			Gdx.app.log("Error", hero.getUnitName() + "가 파티 리스트에 없습니다.");
		}
	}
}
