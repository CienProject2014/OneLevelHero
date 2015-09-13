package com.mygdx.model.location;

import java.util.ArrayList;

public class Dungeon extends SubNode {
	private ArrayList<DungeonFloor> dungeonFloors;
	public ArrayList<DungeonFloor> getDungeonFloors() {
		return dungeonFloors;
	}

	public void setDungeonFloors(ArrayList<DungeonFloor> dungeonFloors) {
		this.dungeonFloors = dungeonFloors;
	}
}
