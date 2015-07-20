package com.mygdx.manager;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.model.Hero;

public class PartyManager {
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private UnitManager unitManager;

	private final static int BATTLE_MEMBER_SIZE = 3;

	public void addHero(Hero hero) {
		// 이미 있는 파티멤버일 경우
		if (partyInfo.getPartyList().contains(hero)) {
			Gdx.app.log("Error", hero.getName() + "가 이미 멤버에 있습니다.");
			return;
		}
		unitManager.initiateHero(hero);
		partyInfo.getPartyList().add(hero);
		Gdx.app.log("Info", hero.getName() + "를 파티에 추가했습니다.");
		if (partyInfo.getBattleMemberList().size() < BATTLE_MEMBER_SIZE) {
			partyInfo.getBattleMemberList().add(hero);

			Gdx.app.log("Info", hero.getName() + "를 전투 멤버에 추가했습니다.");
		} else {
			// 배틀멤버리스트가 꽉찼다면 배틀멤버리스트에 추가할건지 물어본다. 추후 구현
		}
	}

	public void removeHero(Hero hero) {
		if (partyInfo.getBattleMemberList().contains(hero))
			partyInfo.getBattleMemberList().remove(hero);
		partyInfo.getPartyList().remove(hero);
		Gdx.app.log("Info", hero.getName() + "를 파티에서 열외했습니다.");
	}

	public void removeBattleMember(Hero hero) {
		partyInfo.getBattleMemberList().remove(hero);
		Gdx.app.log("Info", hero.getName() + "를 전투 멤버에서 열외했습니다.");
	}

	public void addBattleMember(Hero hero) {
		if (partyInfo.getPartyList().contains(hero)) {
			partyInfo.getBattleMemberList().add(hero);
			Gdx.app.log("Info", hero.getName() + "를 전투 멤버에 추가했습니다.");
		} else
			Gdx.app.log("Error", hero.getName() + "가 파티 리스트에 없습니다.");
	}

	public Hero pickRandomHero() {
		Random random = new Random();
		int index = random.nextInt(partyInfo.getBattleMemberList().size());

		return partyInfo.getBattleMemberList().get(index);
	}

	public int getSelectedIndex() {
		return partyInfo.getSelectedIndex();
	}

	public void setSelectedIndex(int indexNumber) {
		partyInfo.setSelectedIndex(indexNumber);
	}

	public List<Hero> getBattleMemberList() {
		return partyInfo.getBattleMemberList();
	}

	public void setBattleMemberList(List<Hero> battleMemberList) {
		partyInfo.setBattleMemberList(battleMemberList);
	}

	public List<Hero> getPartyList() {
		return partyInfo.getPartyList();
	}

	public void setPartyList(List<Hero> partyList) {
		partyInfo.setPartyList(partyList);
	}
}
