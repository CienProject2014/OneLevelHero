package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.MusicManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;

public class PlayMusicEventTrigger implements EventTrigger {
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		musicManager.setMusicAndPlay(eventParameter.getMusic().getMusicName());
		storySectionManager.runStorySequence();
	}
}
