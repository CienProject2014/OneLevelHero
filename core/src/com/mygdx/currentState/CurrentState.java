package com.mygdx.currentState;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.controller.SaveVersion;

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
	transient private MovingInfo movingInfo; // 가장 최근의 움직임 이력

	// (4) 시간 관리
	@Autowired
	private TimeInfo timeInfo;

	// (5) 보상 미션 및 이벤트, 스토리 분기 관리
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private RewardInfo rewardInfo;
	@Autowired
	private StoryInfo storyInfo;

	// (6) 사운드 관리
	@Autowired
	private MusicInfo musicInfo;

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public RewardInfo getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(RewardInfo rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

	public StoryInfo getStoryInfo() {
		return storyInfo;
	}

	public void setStoryInfo(StoryInfo storyInfo) {
		this.storyInfo = storyInfo;
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

	public InventoryInfo getInventory() {
		return inventoryInfo;
	}

	public InventoryInfo getInventoryInfo() {
		return inventoryInfo;
	}

	public void setInventoryInfo(InventoryInfo inventoryInfo) {
		this.inventoryInfo = inventoryInfo;
	}

	public MusicInfo getMusicInfo() {
		return musicInfo;
	}

	public void setMusicInfo(MusicInfo musicInfo) {
		this.musicInfo = musicInfo;
	}

	public void setInventory(InventoryInfo inventory) {
		this.inventoryInfo = inventory;
	}

	public PartyInfo getPartyInfo() {
		return partyInfo;
	}

	public void setPartyInfo(PartyInfo partyInfo) {
		this.partyInfo = partyInfo;
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
