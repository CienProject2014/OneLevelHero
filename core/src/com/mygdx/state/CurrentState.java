package com.mygdx.state;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import com.mygdx.controller.SaveVersion;
import com.mygdx.model.DungeonInfo;
import com.mygdx.model.Inventory;
import com.mygdx.model.Party;
import com.mygdx.model.RewardInfo;
import com.mygdx.model.VillageInfo;

public class CurrentState implements Serializable {
	// (1) 버전관리
	private SaveVersion saveVersion;

	// (2) 용사 및 파티원 정보, 인벤토리 관리
	private Party party;
	private Inventory inventory;

	// (3) 마을/던전관리
	private VillageInfo villageInfo;
	private DungeonInfo dungeonInfo;

	// (4) 시간 관리

	// (5) 보상 미션 및 퀘스트 관리
	// (5-1) 보상 달성 여부 큐
	private Queue<RewardInfo> rewardQueue;

	// (5-2) 이미 달성한 이벤트 큐
	private Queue<RewardInfo> achievedRewardQueue;

    private static CurrentState instance;

    public static CurrentState getInstance() {
		if (null == instance) {
            instance = new CurrentState();
		}
		return instance;
	}

    private CurrentState() {
		villageInfo = new VillageInfo();
		dungeonInfo = new DungeonInfo();
		party = new Party();
		setRewardQueue(new LinkedList<RewardInfo>());
		setAchievedRewardQueue(new LinkedList<RewardInfo>());
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

	public DungeonInfo getCurrentDungeon() {
		return dungeonInfo;
	}

	private void setDungeonInfo(DungeonInfo dungeonInfo) {
		this.dungeonInfo = dungeonInfo;
	}

	public void setCurrentDungeon(String dungeonID) {
		setDungeonInfo(Assets.dungeonMap.get(dungeonID));
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Queue<RewardInfo> getRewardQueue() {
		return rewardQueue;
	}

	public void setRewardQueue(Queue<RewardInfo> rewardQueue) {
		this.rewardQueue = rewardQueue;
	}

	public Queue<RewardInfo> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	public void setAchievedRewardQueue(Queue<RewardInfo> achievedRewardQueue) {
		this.achievedRewardQueue = achievedRewardQueue;
	}

}
