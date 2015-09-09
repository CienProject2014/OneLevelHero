package com.mygdx.model.location;

import java.util.ArrayList;

import com.mygdx.enums.FieldTypeEnum;

public class Dungeon extends SubNode {
	private FieldTypeEnum fieldType;
	private String sceneNameDoor3;
	private String sceneNameDoor2;
	private String sceneNameDoor1;
	private String sceneNameDoor0;
	private int mapWidth;
	private int mapHeight;
	private boolean inDungeon;

	public ArrayList<DungeonNode> nodes = new ArrayList<>();
	public ArrayList<DungeonConnection> connections = new ArrayList<>();
	public String getSceneName(int doorNum) {
		if (doorNum == 0)
			return sceneNameDoor0;
		else if (doorNum == 1)
			return sceneNameDoor1;
		else if (doorNum == 2)
			return sceneNameDoor2;
		else
			return sceneNameDoor3;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public void setInDungeon(boolean isInDungeon) {
		inDungeon = isInDungeon;
	}
	public boolean isInDungeon() {
		// TODO Auto-generated method stub
		return inDungeon;
	}
	public FieldTypeEnum getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}
}
