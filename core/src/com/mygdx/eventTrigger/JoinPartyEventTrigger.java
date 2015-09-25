package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.EventManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;

public class JoinPartyEventTrigger implements EventTrigger {
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		partyManager.addHero(eventManager.getHeroMap().get(eventParameter.getUnit().getHeroName()));
		storySectionManager.runStorySequence();
	}
}
