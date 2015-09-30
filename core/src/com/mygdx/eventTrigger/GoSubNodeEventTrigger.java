package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.location.Building;

public class GoSubNodeEventTrigger implements EventTrigger {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		Building buildingInfo = eventManager.getTargetBuildingInfo();
		int currentMinute = timeManager.getDayMinute();
		if (buildingInfo.canGoBuilding(currentMinute)) {
			positionManager.setCurrentLocatePositionType(PositionEnum.LocatePosition.SUB_NODE);
			positionManager.setCurrentSubNodePath(buildingInfo.getSubNodePath());
			screenFactory.show(ScreenEnum.BUILDING);
		} else {
			eventManager.setCurrentChatScenes(eventParameter.getEventScenes());
			soundManager.setSoundByPathAndPlay("notice_lock");
			screenFactory.show(ScreenEnum.CHAT_EVENT);
		}
	}
}
