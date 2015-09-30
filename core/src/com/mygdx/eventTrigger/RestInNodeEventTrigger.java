package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.PartyManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;

public class RestInNodeEventTrigger implements EventTrigger {
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		partyManager.healAllHero();
		partyManager.setFatigue(0);
		timeManager.plusHour(4);
	}

}
