package com.mygdx.manager;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.model.unit.Hero;

public class PartyManager {
	@Autowired
	private UnitManager unitManager;
	@Autowired
	private PartyInfo partyInfo;

	public boolean[] isBattleMember = new boolean[3];

	private final static int BATTLE_MEMBER_SIZE = 3;

	public void addHero(Hero hero) {
		// 이미 있는 파티멤버일 경우
		if (partyInfo.getPartyList().contains(hero)) {
			Gdx.app.log("PartyManager", "Error : " + hero.getName() + "가 이미 멤버에 있습니다.");
			return;
		}
		unitManager.initiateHero(hero);
		partyInfo.getPartyList().add(hero);
		Gdx.app.log("PartyManager", hero.getName() + "는 이제부터 파티의 일원이다. 잘 대해주자.");
		if (partyInfo.getBattleMemberList().size() < BATTLE_MEMBER_SIZE) {
			partyInfo.getBattleMemberList().add(hero);

			Gdx.app.log("PartyManager", hero.getName() + "는 이제부터 전투의 일원이다. 훌륭한 장수로 키우자.");
		} else {
			// 배틀멤버리스트가 꽉찼다면 배틀멤버리스트에 추가할건지 물어본다. 추후 구현
		}
	}

	public void setFatigue(int fatigue) {
		if (fatigue < 0) {
			fatigue = 0;
		}
		partyInfo.setFatigue(fatigue);
	}

	public int getFatigue() {
		return partyInfo.getFatigue();
	}

	public void healAllHero() {
		for (Hero hero : getBattleMemberList()) {
			hero.getStatus().setHp(hero.getStatus().getMaxHp());
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
		// 몬스터 공격할때 영웅 뽑는 기준 : 어그로
		int sumAggro = 0;
		float percent = 0;
		int nGetVal = (int) Math.round(Math.random() * 100);

		for (Hero hero : partyInfo.getBattleMemberList()) {
			sumAggro += hero.getRealAggro();
		}
		for (Hero hero : partyInfo.getBattleMemberList()) {
			hero.setPercent((hero.getRealAggro() * 100 / sumAggro));
		}
		for (Hero hero : partyInfo.getBattleMemberList()) {
			percent += hero.getPercent();
			if ((int) percent >= nGetVal) {
				return hero;
			}
		}
		// 혹시 버그가있다면..
		return partyInfo.getBattleMemberList()
				.get(ThreadLocalRandom.current().nextInt(partyInfo.getBattleMemberList().size()));
	}

	public void calculateLevel() {
		for (Hero hero : getPartyList()) {

			if (hero.getStatus().getLevel() == 1) {
			} else {
				if (hero.getStatus().getExperience() < 0) {
					int exp = hero.getStatus().getExperience();
					levelDown(hero);
					hero.getStatus().setExperience(hero.getStatus().getMaxExperience() + exp);
				} else if (hero.getStatus().getExperience() >= hero.getStatus().getMaxExperience()) {
					int exp = hero.getStatus().getExperience() - hero.getStatus().getMaxExperience();
					levelUp(hero);
					hero.getStatus().setExperience(exp);
				} else {

				}
			}
		}

	}

	private void levelUp(Hero hero) {
		switch (hero.getFacePath()) {
		case "yongsa":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0.25));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() + 0.25));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0.25));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0.25));
			hero.getStatus().setSpeed(hero.getStatus().getSpeed() + 1);
			break;
		case "parath":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0.2));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack()));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0.25));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 0.5));
			break;
		case "lilis_succubus":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() + 0.25));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0.2));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 0.5));
			break;
		case "plecha":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0.34));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack()));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0.2));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0.25));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 1));
			break;
		case "yalluisroy":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0.25));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() + 0.25));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0.2));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0.2));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 1));
			break;
		case "diomirhone":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() + 0.25));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0.2));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 0.5));
			break;
		case "bersus":
			hero.getStatus().setLevel(hero.getStatus().getLevel() + 1);
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() + 1);
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack() + 0));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() + 0.34));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0.25));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 0.5));
			break;
		case "kein":
			hero.getStatus().setLevel(hero.getStatus().getLevel());
			hero.getStatus().setMaxHp(hero.getStatus().getMaxHp());
			hero.getStatus().setAttack((float) (hero.getStatus().getAttack()));
			hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() + 0));
			hero.getStatus().setDefense((float) (hero.getStatus().getDefense() + 0));
			hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() + 0));
			hero.getStatus().setSpeed((float) (hero.getStatus().getSpeed() + 0));
			break;

		}
	}

	private void levelDown(Hero hero) {
		// 체력+1 공격력+1/4 주문력+1/4 방어력+1/4 항마력+1/4 민첩성+1
		if (hero.getStatus().getHp() == hero.getStatus().getMaxHp()) {
			hero.getStatus().setHp(hero.getStatus().getHp() - 1);
		}
		hero.getStatus().setLevel(hero.getStatus().getLevel() - 1);
		hero.getStatus().setMaxHp(hero.getStatus().getMaxHp() - 1);
		hero.getStatus().setAttack((float) (hero.getStatus().getAttack() - 0.25));
		hero.getStatus().setMagicAttack((float) (hero.getStatus().getMagicAttack() - 0.25));
		hero.getStatus().setDefense((float) (hero.getStatus().getDefense() - 0.25));
		hero.getStatus().setMagicDefense((float) (hero.getStatus().getMagicDefense() - 0.25));
		hero.getStatus().setSpeed(hero.getStatus().getSpeed() - 1);
	}

	public void setCurrentSelectedHero(Hero hero) {
		partyInfo.setCurrentSelectedHero(hero);
	}

	public Hero getCurrentSelectedHero() {
		return partyInfo.getCurrentSelectedHero();
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
