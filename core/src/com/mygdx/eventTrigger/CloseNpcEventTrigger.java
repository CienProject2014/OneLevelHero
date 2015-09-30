package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.EventParameters;

public class CloseNpcEventTrigger implements EventTrigger {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		EventPacket eventPacket = eventParameter.getEventPacket();
		eventManager.setGameObjectEventState(eventPacket, EventStateEnum.CLOSED);
		storySectionManager.runStorySequence();
	}

}
