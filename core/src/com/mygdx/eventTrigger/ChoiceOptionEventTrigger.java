package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.event.EventParameters;

public class ChoiceOptionEventTrigger implements EventTrigger {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private EventManager eventManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		eventManager.setCurrentChatScenes(eventParameter.getEventScenes());
		screenFactory.show(ScreenEnum.CHOICE_OPTION);
	}

}
