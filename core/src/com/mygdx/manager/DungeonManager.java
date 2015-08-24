package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.NodeAssets;
import com.mygdx.model.Dungeon;

public class DungeonManager {

	@Autowired
	private NodeAssets nodeAssets;
	private boolean isInDungeon;
	private Dungeon mapInfo;
	private boolean currentHeading;
	private int currentPos;

	public int getCurrentPos() {
		return currentPos;
	}
	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}
	public boolean getCurrentHeading() {
		return currentHeading;
	}
	public void changeCurrentHeading() {
		this.currentHeading = !currentHeading;
	}
	public Dungeon getMapInfo() {
		return mapInfo;
	}
	public void setMapInfo(String dungeonName) {
		mapInfo = nodeAssets.getDungeonByName("devil_castle_dungeon");
	}
	public boolean isInDungeon() {
		return isInDungeon;
	}
	public void setInDungeon(boolean isInDungeon) {
		this.isInDungeon = isInDungeon;
	}
}
