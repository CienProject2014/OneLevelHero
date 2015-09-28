package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;

public class PassTimeEventTrigger implements EventTrigger {
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		timeManager.setTime(eventParameter.getTime().getMinute() * 60);
		storySectionManager.runStorySequence();
	}
}
