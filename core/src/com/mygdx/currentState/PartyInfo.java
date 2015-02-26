package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.Hero;

@Component
public class PartyInfo {
	private List<Hero> partyList = new ArrayList<Hero>();
	private List<Hero> battleMemberList = new ArrayList<Hero>();
	private int selectedInedex = 0;

	public int getSelectedInedex() {
		return selectedInedex;
	}

	public void setSelectedInedex(int selectedInedex) {
		this.selectedInedex = selectedInedex;
	}

	private final static int BATTLE_MEMBER_SIZE = 3;

	public List<Hero> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<Hero> partyList) {
		this.partyList = partyList;
	}

	public void addHero(Hero hero) {
		// 이미 있는 파티멤버일 경우
		if (partyList.contains(hero)) {
			Gdx.app.log("Error", hero.getName() + "가 이미 멤버에 있습니다.");
			return;
		}
		partyList.add(hero);
		Gdx.app.log("Info", hero.getName() + "를 파티에 추가했습니다.");
		if (battleMemberList.size() < BATTLE_MEMBER_SIZE) {
			battleMemberList.add(hero);

			Gdx.app.log("Info", hero.getName() + "를 전투 멤버에 추가했습니다.");
		} else {
			// 배틀멤버리스트가 꽉찼다면 배틀멤버리스트에 추가할건지 물어본다. 추후 구현
		}
	}

	public void removeHero(Hero hero) {
		if (battleMemberList.contains(hero))
			battleMemberList.remove(hero);
		partyList.remove(hero);
		Gdx.app.log("Info", hero.getName() + "를 파티에서 열외했습니다.");
	}

	public void removeBattleMember(Hero hero) {
		battleMemberList.remove(hero);
		Gdx.app.log("Info", hero.getName() + "를 전투 멤버에서 열외했습니다.");
	}

	public void addBattleMember(Hero hero) {

		if (partyList.contains(hero)) {
			battleMemberList.add(hero);
			Gdx.app.log("Info", hero.getName() + "를 전투 멤버에 추가했습니다.");
		} else {
			Gdx.app.log("Error", hero.getName() + "가 파티 리스트에 없습니다.");
		}
	}

	public List<Hero> getBattleMemberList() {
		return battleMemberList;
	}

	public void setBattleMemberList(List<Hero> battleMemberList) {
		this.battleMemberList = battleMemberList;
	}

	public Hero pickRandomHero() {
		Random random = new Random();
		int index = random.nextInt(battleMemberList.size());

		return battleMemberList.get(index);
	}

}
