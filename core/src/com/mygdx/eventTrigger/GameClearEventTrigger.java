package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.event.EventParameters;

public class GameClearEventTrigger implements EventTrigger {
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		screenFactory.show(ScreenEnum.GAME_CLEAR);
	}
}
