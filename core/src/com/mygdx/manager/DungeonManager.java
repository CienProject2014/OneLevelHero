package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.NodeAssets;
import com.mygdx.currentState.DungeonInfo;
import com.mygdx.enums.DungeonEnum;
import com.mygdx.enums.DungeonEnum.Direction;
import com.mygdx.model.location.Dungeon;
import com.mygdx.model.location.DungeonConnection;
import com.mygdx.model.location.DungeonRoom;

public class DungeonManager {
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private MovingManager movingManager;
	private DungeonInfo dungeonInfo = new DungeonInfo();

	public DungeonInfo getDungeonInfo() {
		return dungeonInfo;
	}

	public void setInitialDungeonInfo(String dungeonPath) {
		Dungeon currentDungeon = nodeAssets.getDungeonByPath(dungeonPath);
		dungeonInfo.setCurrentDungeon(currentDungeon);
		dungeonInfo.setCurrentFloor(currentDungeon.getDungeonFloors().get(0));
		setInitialCurrentRoomInfo(dungeonInfo.getStartDungeonRoomIndex());
		setFloorMinimap(dungeonInfo);
		setCurrentRoomVisibilityOn();
	}

	private void setFloorMinimap(DungeonInfo dungeonInfo) {
		if (dungeonInfo.getCurrentFloor().getMiniMap() == null) {
			boolean[][] minimapInfo = new boolean[dungeonInfo.getCurrentFloor().getMapHeight()][dungeonInfo
					.getCurrentFloor().getMapWidth()];
			dungeonInfo.getCurrentFloor().setMiniMap(minimapInfo);
		}
	}

	private void setInitialCurrentRoomInfo(int startDungeonRoomIndex) {
		dungeonInfo.setCurrentRoom(dungeonInfo.getCurrentFloor().getDungeonRooms().get(startDungeonRoomIndex));
		dungeonInfo.setCurrentDirection(Direction.FORWARD);
		dungeonInfo.setCurrentForwardAngle(dungeonInfo.getCurrentRoom().getForwardAngle());
		setCurrentDoorSize(dungeonInfo);
	}

	public int getCurrentDoorSize() {
		return dungeonInfo.getCurrentDoorSize();
	}

	public void setCurrentDoorSize(DungeonInfo dungeonInfo) {
		int doorSize;
		if (dungeonInfo.getCurrentDirection().equals(DungeonEnum.Direction.FORWARD)) {
			doorSize = dungeonInfo.getCurrentRoom().getForwardConnections().size();
		} else if (dungeonInfo.getCurrentDirection().equals(DungeonEnum.Direction.BACKWARD)) {
			doorSize = dungeonInfo.getCurrentRoom().getBackwardConnections().size();
		} else {
			Gdx.app.log("DungeonManager", "Direction정보 오류");
			doorSize = 0;
		}
		dungeonInfo.setCurrentDoorSize(doorSize);
	}

	public void setCurrentRoomVisibilityOn() {
		dungeonInfo.getCurrentFloor().setMiniMapAttribute(dungeonInfo.getCurrentRoom().getRoomPosY(),
				dungeonInfo.getCurrentRoom().getRoomPosX());
	}

	public void moveRoom(int index) {
		DungeonRoom originalRoom = dungeonInfo.getCurrentRoom();
		DungeonConnection dungeonConnection;
		if (dungeonInfo.getCurrentDirection().equals(Direction.FORWARD)) {
			dungeonConnection = originalRoom.getForwardConnections().get(index);
		} else {
			dungeonConnection = originalRoom.getBackwardConnections().get(index);
		}
		DungeonRoom targetRoom = findDungeonRoomByLabel(dungeonConnection.getRoomLabel());
		dungeonInfo.setCurrentRoom(targetRoom);
		dungeonInfo.setCurrentDirection(dungeonConnection.getDirectionType());
		dungeonInfo.getCurrentFloor().setMiniMapAttribute(targetRoom.getRoomPosY(), targetRoom.getRoomPosX());
		setCurrentDoorSize(dungeonInfo);
	}

	private DungeonRoom findDungeonRoomByLabel(String roomLabel) {
		Iterator<DungeonRoom> roomIterator = dungeonInfo.getCurrentFloor().getDungeonRooms().iterator();
		while (roomIterator.hasNext()) {
			DungeonRoom dungeonRoom = roomIterator.next();
			if (dungeonRoom.getRoomLabel().equals(roomLabel)) {
				return dungeonRoom;
			}
		}
		Gdx.app.log("DungeonManager", "roomLabel 정보 오류 - " + roomLabel);
		return null;
	}

	public void changeDirection() {
		if (dungeonInfo.getCurrentDirection().equals(Direction.FORWARD)) {
			dungeonInfo.setCurrentDirection(Direction.BACKWARD);
		} else {
			dungeonInfo.setCurrentDirection(Direction.FORWARD);
		}
		setCurrentDoorSize(dungeonInfo);
	}

	public void leaveDungeon() {
		String nodeName = dungeonInfo.getCurrentRoom().getLink();
		movingManager.goToNode(nodeName);
	}
}
