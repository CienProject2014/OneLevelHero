package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;

public class MoveNodeEventTrigger implements EventTrigger {
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		positionManager.setCurrentNodeName(eventParameter.getLocation().getNodeName());
		movingManager.goCurrentLocatePosition();
		storySectionManager.runStorySequence();
	}
}
