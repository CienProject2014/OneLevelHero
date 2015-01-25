package com.mygdx.currentState;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.controller.SaveVersion;

@Component
public class CurrentState implements Serializable {
	// (1) 버전관리
	private SaveVersion saveVersion;

	// (2) 용사 및 파티원 정보, 인벤토리 관리
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private InventoryInfo inventoryInfo;

	// (3) 마을/무빙로드관리
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	transient private MovingInfo movingInfo; //가장 최근의 움직임 이력

	// (4) 시간 관리
	@Autowired
	private TimeInfo timeInfo;

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
		setRewardQueue(new LinkedList<RewardInfo>());
		setAchievedRewardQueue(new LinkedList<RewardInfo>());
		setPositionInfo(new PositionInfo());
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

	public PartyInfo getParty() {
		return partyInfo;
	}

	public void setParty(PartyInfo party) {
		this.partyInfo = party;
	}

	public InventoryInfo getInventory() {
		return inventoryInfo;
	}

	public void setInventory(InventoryInfo inventory) {
		this.inventoryInfo = inventory;
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

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public TimeInfo getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(TimeInfo timeInfo) {
		this.timeInfo = timeInfo;
	}

	public MovingInfo getMovingInfo() {
		return movingInfo;
	}

	public void setMovingInfo(MovingInfo movingInfo) {
		this.movingInfo = movingInfo;
	}

}
