package com.mygdx.event;

import com.mygdx.unit.Hero;
import com.mygdx.util.CurrentManager;

public class PartyRewardManager extends RewardManager {
	private String NpcName = "";

	PartyRewardManager() {
		NpcName = RewardManager.getInstance().getRewardKey().getKeyOfRewardValue();
	}

	public String getNpcName() {
		return NpcName;
	}

	public String getRewardMessage() {
		NpcName = RewardManager.getInstance().getRewardKey().getKeyOfRewardValue();
		return NpcName + "가 파티에 합류했습니다.";
	}

	public void doReward() {
		Hero hero = new Hero(NpcName);
		CurrentManager.getInstance().party.addParty(hero);
	}
}
