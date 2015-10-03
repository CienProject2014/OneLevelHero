package com.mygdx.eventTrigger;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.event.EventParameters;

public class NoEventTrigger implements EventTrigger {

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		Gdx.app.log("NoEventTrigger", "NoEventTrigger 발생");
	}

}
