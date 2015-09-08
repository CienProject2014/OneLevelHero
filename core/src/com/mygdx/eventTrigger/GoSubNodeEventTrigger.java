package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.location.Building;
import com.mygdx.nextSectionChecker.ArgumentChecker;

public class GoSubNodeEventTrigger implements EventTrigger {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionManager positionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		Building buildingInfo = eventManager.getTargetBuildingInfo();
		int currentMinute = timeManager.getDayMinute();
		if (ArgumentChecker.checkIsInTargetTime(buildingInfo.getTargetTime(), currentMinute)) {
			positionManager.setCurrentLocatePositionType(PositionEnum.LocatePosition.SUB_NODE);
			positionManager.setCurrentSubNodeName(buildingInfo.getSubNodePath());
			screenFactory.show(ScreenEnum.BUILDING);
		} else {
			screenFactory.show(ScreenEnum.CHAT_EVENT);
		}
	}
}
