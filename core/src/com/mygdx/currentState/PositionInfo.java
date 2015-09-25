package com.mygdx.currentState;

import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.VillageDirectionEnum;

public class PositionInfo {
	private VillageDirectionEnum villageDirection;
	private PositionEnum.LocatePosition currentLocatePositionType; // 플레이어의 위치
	private PositionEnum.EventPosition currentEventPositionType;
	private String currentNodePath; // 최근 마을/던전입구/교차로/필드 이름
	private String currentSubNodePath; // 최근 건물/던전이름
	private String currentDungeonRoomPath; // 던전룸이름

	public String getCurrentSubNodePath() {
		return currentSubNodePath;
	}

	public void setCurrentSubNodePath(String currentSubNodePath) {
		this.currentSubNodePath = currentSubNodePath;
	}

	public String getCurrentNodePath() {
		return currentNodePath;
	}

	public void setCurrentNodePath(String currentNodePath) {
		this.currentNodePath = currentNodePath;
	}

	public PositionEnum.LocatePosition getCurrentLocatePositionType() {
		return currentLocatePositionType;
	}

	public void setCurrentLocatePositionType(PositionEnum.LocatePosition currentPositionType) {
		this.currentLocatePositionType = currentPositionType;
	}

	public VillageDirectionEnum getVillageDirection() {
		return villageDirection;
	}

	public void setVillageDirection(VillageDirectionEnum villageDirection) {
		this.villageDirection = villageDirection;
	}

	public PositionEnum.EventPosition getCurrentEventPositionType() {
		return currentEventPositionType;
	}

	public void setCurrentEventPositionType(PositionEnum.EventPosition currentEventPositionType) {
		this.currentEventPositionType = currentEventPositionType;
	}

	public String getCurrentDungeonRoomPath() {
		return currentDungeonRoomPath;
	}

	public void setCurrentDungeonRoomPath(String currentDungeonRoomPath) {
		this.currentDungeonRoomPath = currentDungeonRoomPath;
	}
}
