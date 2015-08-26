package com.mygdx.model.surroundings;

import java.util.ArrayList;

import com.mygdx.enums.FieldTypeEnum;

public class Dungeon {
	private FieldTypeEnum fieldType;
	private String name;
	private String dungeonPath;
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
	public String getDungeonName() {
		return dungeonPath;
	}
	public void setDungeonName(String dungeonName) {
		this.dungeonPath = dungeonName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FieldTypeEnum getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}
}
