package com.mygdx.manager;

import java.io.Serializable;

import com.mygdx.model.Hero;
import com.mygdx.model.Inventory;
import com.mygdx.model.Party;
import com.mygdx.model.VillageInfo;
import com.mygdx.resource.SaveVersion;

public class CurrentManager implements Serializable {
	// (1) 버전관리
	private SaveVersion saveVersion;

	// (2) 용사 및 파티원 정보, 인벤토리 관리
	private Hero hero;
	private Party party;
	private Inventory inventory;

	// (3) 마을 관리
	private VillageInfo villageInfo;

	// (4) 시간 관리

	// (5) 보상 미션 및 퀘스트 관리
	private RewardInfo rewardInfo;

	private static CurrentManager instance;

	public static CurrentManager getInstance() {
		if (null == instance) {
			instance = new CurrentManager();
		}
		return instance;
	}

	private CurrentManager() {
		villageInfo = new VillageInfo();
		party = new Party();
		rewardInfo = new RewardInfo();
	}

	public SaveVersion getVersion() {
		return saveVersion;
	}

	public void setVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
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

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public VillageInfo getVillageInfo() {
		return villageInfo;
	}

	public void setVillageInfo(VillageInfo villageInfo) {
		this.villageInfo = villageInfo;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public RewardInfo getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(RewardInfo rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}
