package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;

public class DungeonDoorButtonListener extends ClickListener {
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private TimeManager timeManager;

	private int index;
	@Override
	public void clicked(InputEvent event, float x, float y) {
		dungeonManager.moveRoom(index);
		String subNodePath = positionManager.getCurrentSubNodePath();
		String floorPath = dungeonManager.getDungeonInfo().getCurrentFloor().getFloorPath();
		String roomLabel = dungeonManager.getDungeonInfo().getCurrentRoom().getRoomLabel();
		timeManager.plusMinute(10);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_DUNGEON_ROOM_IN_TARGET_TIME, subNodePath,
				floorPath, roomLabel);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_DUNGEON_ROOM_BEFORE_ABSOLUTE_TIME, subNodePath,
				floorPath, roomLabel);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_DUNGEON_ROOM_AFTER_ABSOLUTE_TIME, subNodePath,
				floorPath, roomLabel);

	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
