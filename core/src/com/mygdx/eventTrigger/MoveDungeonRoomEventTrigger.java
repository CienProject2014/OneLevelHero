package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.PositionEnum.LocatePosition;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.event.EventParameters;

public class MoveDungeonRoomEventTrigger implements EventTrigger {
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private PositionManager positionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		positionManager.setCurrentEventPositionType(EventPosition.DUNGEON);
		positionManager.setCurrentLocatePositionType(LocatePosition.DUNGEON);
		dungeonManager.moveDungeonByEvent(eventParameter.getLocation());
	}
}
