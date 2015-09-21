package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;

public class DungeonDoorButtonListener extends ClickListener {
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;

	private int index;
	@Override
	public void clicked(InputEvent event, float x, float y) {
		dungeonManager.moveRoom(index);
		String subNodePath = positionManager.getCurrentSubNodePath();
		String floorPath = dungeonManager.getDungeonInfo().getCurrentFloor().getFloorPath();
		String roomLabel = dungeonManager.getDungeonInfo().getCurrentRoom().getRoomLabel();

		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_DUNGEON_ROOM_IN_TARGET_TIME, subNodePath,
				floorPath, roomLabel);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
