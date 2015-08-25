package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.NodeAssets;
import com.mygdx.model.surroundings.Dungeon;

public class DungeonManager {
	@Autowired
	private NodeAssets nodeAssets;
	private Dungeon mapInfo;
	private boolean currentHeading;
	private int currentPos;
	private boolean[][] isOn;

	public void setIsOn() {
		isOn = new boolean[mapInfo.getMapWidth()][mapInfo.getMapHeight()];
	}
	public void turnIsOn(int x, int y) {

		if (isOn == null)
			setIsOn();
		isOn[x][y] = true;
	}
	public boolean checkIsOn(int x, int y) {
		return isOn[x][y];
	}

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
	public void setMapInfo(String dungeonPath) {
		mapInfo = nodeAssets.getDungeonByName(dungeonPath);
		mapInfo.setInDungeon(true);
	}

	public void setInDungeon(boolean isInDungeon) {
		if (mapInfo != null) {
			mapInfo.setInDungeon(isInDungeon);
		}
	}

	public boolean isInDungeon() {
		// TODO Auto-generated method stub
		if (mapInfo != null)
			return mapInfo.isInDungeon();
		else
			return false;
	}
}
