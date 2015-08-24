package com.mygdx.model;

import java.util.ArrayList;

public class Dungeon {
	private String dungeonName;
	private String sceneName;
	private int mapWidth;
	private int mapHeight;
	private boolean inDungeon;

	public ArrayList<DungeonNode> nodes = new ArrayList<>();
	public ArrayList<DungeonConnection> connections = new ArrayList<>();
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
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
}
