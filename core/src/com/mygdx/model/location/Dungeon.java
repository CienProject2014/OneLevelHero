package com.mygdx.model.location;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;

public class Dungeon extends SubNode {
	private ArrayList<DungeonFloor> dungeonFloors;
	public ArrayList<DungeonFloor> getDungeonFloors() {
		return dungeonFloors;
	}

	public DungeonFloor findFloorByPath(String floorPath) {
		Iterator<DungeonFloor> floorIterator = dungeonFloors.iterator();
		while (floorIterator.hasNext()) {
			DungeonFloor dungeonFloor = floorIterator.next();
			if (dungeonFloor.getFloorPath().equals(floorPath)) {
				return dungeonFloor;
			}
		}
		Gdx.app.log("Dungeon", "잘못된 dungeonFloorPath 정보" + floorPath);
		return null;
	}

	public void setDungeonFloors(ArrayList<DungeonFloor> dungeonFloors) {
		this.dungeonFloors = dungeonFloors;
	}
}
