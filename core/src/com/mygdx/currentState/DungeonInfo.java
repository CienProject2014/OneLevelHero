package com.mygdx.currentState;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.DungeonEnum;
import com.mygdx.model.location.Dungeon;
import com.mygdx.model.location.DungeonFloor;
import com.mygdx.model.location.DungeonRoom;

public class DungeonInfo {
	private boolean isInDungeon;
	private Dungeon currentDungeon;
	private DungeonFloor currentFloor;
	private DungeonRoom currentRoom;
	private DungeonRoom beforeRoom;
	private DungeonEnum.ForwardAngle currentForwardAngle;
	private DungeonEnum.Direction currentDirection;
	private int currentDoorSize;
	private int startDungeonRoomIndex;

	public DungeonEnum.Direction getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(DungeonEnum.Direction currentDirection) {
		this.currentDirection = currentDirection;
	}
	public Dungeon getCurrentDungeon() {
		return currentDungeon;
	}
	public void setCurrentDungeon(Dungeon currentDungeon) {
		this.currentDungeon = currentDungeon;
	}
	public DungeonFloor getCurrentFloor() {
		return currentFloor;
	}
	public void setCurrentFloor(DungeonFloor currentFloor) {
		this.currentFloor = currentFloor;
	}
	public DungeonRoom getCurrentRoom() {
		return currentRoom;
	}
	public void setCurrentRoom(DungeonRoom currentRoom) {
		Gdx.app.log("DungeonInfo", "CurrentDungeonRoomInfo - " + currentRoom.getRoomLabel());
		this.currentRoom = currentRoom;
	}
	public int getStartDungeonRoomIndex() {
		return startDungeonRoomIndex;
	}
	public void setStartDungeonRoomIndex(int startDungeonRoomIndex) {
		this.startDungeonRoomIndex = startDungeonRoomIndex;
	}
	public DungeonEnum.ForwardAngle getCurrentForwardAngle() {
		return currentForwardAngle;
	}
	public void setCurrentForwardAngle(DungeonEnum.ForwardAngle currentForwardAngle) {
		this.currentForwardAngle = currentForwardAngle;
	}
	public int getCurrentDoorSize() {
		return currentDoorSize;
	}
	public void setCurrentDoorSize(int currentDoorSize) {
		this.currentDoorSize = currentDoorSize;
	}
	public DungeonRoom getBeforeRoom() {
		return beforeRoom;
	}
	public void setBeforeRoom(DungeonRoom beforeRoom) {
		this.beforeRoom = beforeRoom;
	}
	public boolean isInDungeon() {
		return isInDungeon;
	}
	public void setInDungeon(boolean isInDungeon) {
		this.isInDungeon = isInDungeon;
	}
}
