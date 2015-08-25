package com.mygdx.currentState;

import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.VillageDirectionEnum;

public class PositionInfo {
	private VillageDirectionEnum villageDirection;
	private PositionEnum currentPositionType; // 플레이어의 위치가 마을인지, 갈림길인지, 던전인지,
												// 건물안인지, 필드인지
	private String currentNodeName; // 최근 마을/던전입구/교차로/필드 이름
	private String currentSubNodeName; // 최근 건물/던전이름
	private PositionEnum beforePositionType;

	public String getCurrentSubNodeName() {
		return currentSubNodeName;
	}

	public void setCurrentSubNodeName(String currentSubNodeName) {
		this.currentSubNodeName = currentSubNodeName;
	}

	public String getCurrentNodeName() {
		return currentNodeName;
	}

	public void setCurrentNodeName(String currentNodeName) {
		this.currentNodeName = currentNodeName;
	}

	public PositionEnum getCurrentPositionType() {
		return currentPositionType;
	}

	public void setCurrentPositionType(PositionEnum currentPositionType) {
		this.currentPositionType = currentPositionType;
	}

	public PositionEnum getBeforePositionType() {
		return beforePositionType;
	}

	public void setBeforePositionType(PositionEnum beforePositionType) {
		this.beforePositionType = beforePositionType;
	}

	public VillageDirectionEnum getVillageDirection() {
		return villageDirection;
	}

	public void setVillageDirection(VillageDirectionEnum villageDirection) {
		this.villageDirection = villageDirection;
	}
}
