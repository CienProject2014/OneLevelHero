package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.PartyManager;
import com.mygdx.model.event.EventParameters;

public class RestInForkEventTrigger implements EventTrigger {
	@Autowired
	private PartyManager partyManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		partyManager.healAllHero();
		partyManager.setFatigue(partyManager.getFatigue() - 15);
	}

}
