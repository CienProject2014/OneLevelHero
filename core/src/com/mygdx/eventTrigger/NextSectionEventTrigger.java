package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;

public class NextSectionEventTrigger implements EventTrigger {
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		storySectionManager.setNewStorySectionAndPlay(Integer.valueOf(eventParameter.getNextSectionNumber()));
	}
}
