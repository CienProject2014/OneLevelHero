package com.mygdx.model.location;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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

	public ArrayList<DungeonRoom> dungeonRooms = new ArrayList<>();
	public ArrayList<DungeonConnection> connections = new ArrayList<>();
	public String getSceneName(int doorNum) {
		switch (doorNum) {
			case 0 :
				return sceneNameDoor0;
			case 1 :
				return sceneNameDoor1;
			case 2 :
				return sceneNameDoor2;
			case 3 :
				return sceneNameDoor3;
			default :
				Gdx.app.log("Dungeon", "doorNum정보 오류 -" + doorNum);
				return null;
		}
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
		return inDungeon;
	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}
}
