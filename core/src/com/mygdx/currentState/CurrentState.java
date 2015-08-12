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
	private BagInfo inventoryInfo;

	// (3) 마을/무빙로드관리
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	transient private FieldInfo movingInfo; // 가장 최근의 움직임 이력

	// (4) 시간 관리
	@Autowired
	private TimeInfo timeInfo;

	// (5) 보상 미션 및 이벤트 관리
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private RewardInfo rewardQueueInfo;
	@Autowired
	private StorySectionInfo storySectionInfo;

	// (6) 사운드 관리
	@Autowired
	private MusicInfo musicInfo;

	public SaveVersion getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}
}
