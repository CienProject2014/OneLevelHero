package com.mygdx.model.location;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;

public class DungeonFloor {
	private String floorPath;
	private String floorName;
	private int mapHeight;
	private int mapWidth;
	private boolean miniMap[][];
	private ArrayList<DungeonRoom> dungeonRooms = new ArrayList<>();
	private ArrayList<String> floorMonsterList;

	public DungeonRoom findRoomByLabel(String roomLabel) {
		Iterator<DungeonRoom> dungeonRoomIterator = dungeonRooms.iterator();
		while (dungeonRoomIterator.hasNext()) {
			DungeonRoom dungeonRoom = dungeonRoomIterator.next();
			if (dungeonRoom.getRoomLabel().equals(roomLabel)) {
				return dungeonRoom;
			}
		}
		Gdx.app.log("DungeonFloor", "DungeonRoomLabel정보 오류" + roomLabel);
		return null;
	}

	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public ArrayList<DungeonRoom> getDungeonRooms() {
		return dungeonRooms;
	}
	public void setDungeonRooms(ArrayList<DungeonRoom> dungeonRooms) {
		this.dungeonRooms = dungeonRooms;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public boolean[][] getMiniMap() {
		return miniMap;
	}
	public void setMiniMapAttribute(int posY, int posX) {
		miniMap[posY - 1][posX - 1] = true;
	}
	public void setMiniMap(boolean miniMap[][]) {
		this.miniMap = miniMap;
	}
	public ArrayList<String> getFloorMonsterList() {
		return floorMonsterList;
	}
	public void setFloorMonsterList(ArrayList<String> floorMonsterList) {
		this.floorMonsterList = floorMonsterList;
	}
	public String getFloorPath() {
		return floorPath;
	}
	public void setFloorPath(String floorPath) {
		this.floorPath = floorPath;
	}
}
