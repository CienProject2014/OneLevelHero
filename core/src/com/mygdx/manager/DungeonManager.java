package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.NodeAssets;
import com.mygdx.model.location.Dungeon;

public class DungeonManager {
	@Autowired
	private NodeAssets nodeAssets;
	private Dungeon dungeonInfo;
	private boolean currentHeading;
	private int currentPos;
	private boolean[][] isOn;
	private String recentDungeon;

	public void setRecentDungeon(String currentDungeon) {
		recentDungeon = currentDungeon;
	}

	public boolean isThisNewDungeon(String newDungeon) {
		if (recentDungeon != null)
			return recentDungeon.equals(newDungeon);
		else
			return true;
	}

	public void setIsOn() {
		isOn = new boolean[dungeonInfo.getMapHeight()][dungeonInfo.getMapWidth()];
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
	public Dungeon getDungeonInfo() {
		return dungeonInfo;
	}
	public void setDungeonInfo(String dungeonPath) {
		dungeonInfo = nodeAssets.getDungeonByName(dungeonPath);
		if (dungeonInfo == null)
			setDungeonInfo("devil_castle");
		dungeonInfo.setInDungeon(true);
	}

	public void setInDungeon(boolean isInDungeon) {
		if (dungeonInfo != null) {
			dungeonInfo.setInDungeon(isInDungeon);
		}
	}

	public boolean isInDungeon() {
		if (dungeonInfo != null)
			return dungeonInfo.isInDungeon();
		else
			return false;
	}
}
