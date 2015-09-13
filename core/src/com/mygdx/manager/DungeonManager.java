package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.NodeAssets;
import com.mygdx.currentState.DungeonInfo;
import com.mygdx.enums.DungeonEnum;
import com.mygdx.model.location.Dungeon;

public class DungeonManager {
	public static final String DOOR_SIZE_3_SCENE = "dungeon_3door_scene";
	public static final String DOOR_SIZE_2_SCENE = "dungeon_2door_scene";
	public static final String DOOR_SIZE_1_SCENE = "dungeon_1door_scene";
	public static final String DOOR_SIZE_0_SCENE = "dungeon_0door_scene";
	@Autowired
	private NodeAssets nodeAssets;
	private DungeonInfo dungeonInfo;
	private DungeonEnum.Direction currentDirection;
	public DungeonInfo getDungeonInfo() {
		return dungeonInfo;
	}

	public void setDungeonInfo(String dungeonPath) {
		Dungeon currentDungeon = nodeAssets.getDungeonByPath(dungeonPath);
		dungeonInfo.setCurrentDungeon(currentDungeon);
		dungeonInfo.setCurrentFloor(currentDungeon.getDungeonFloors().get(0));
		dungeonInfo.setCurrentRoom(dungeonInfo.getCurrentFloor().getDungeonRooms()
				.get(dungeonInfo.getStartDungeonRoomIndex()));
		setCurrentDoorSize(dungeonInfo);
	}

	public int getCurrentDoorSize() {
		return dungeonInfo.getCurrentDoorSize();
	}

	public void setCurrentDoorSize(DungeonInfo dungeonInfo) {
		int doorSize;
		if (dungeonInfo.getCurrentDirection().equals(DungeonEnum.Direction.FORWARD)) {
			doorSize = dungeonInfo.getCurrentRoom().getForwardConnections().size();
		} else if (dungeonInfo.getCurrentDirection().equals(DungeonEnum.Direction.BACKWARD)) {
			doorSize = dungeonInfo.getCurrentRoom().getBackwardConnections().size();
		} else {
			Gdx.app.log("DungeonManager", "Direction정보 오류");
			doorSize = 0;
		}
		dungeonInfo.setCurrentDoorSize(doorSize);
	}

	public DungeonEnum.Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(DungeonEnum.Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	public String getSceneName() {
		switch (dungeonInfo.getCurrentDoorSize()) {
			case 0 :
				return DOOR_SIZE_0_SCENE;
			case 1 :
				return DOOR_SIZE_1_SCENE;
			case 2 :
				return DOOR_SIZE_2_SCENE;
			case 3 :
				return DOOR_SIZE_3_SCENE;
			default :
				Gdx.app.log("DungeonManager", "doorSize 정보 오류");
				return null;
		}
	}
}
