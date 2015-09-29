package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.NodeAssets;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.location.TargetTime;

public class SetGameObjectTargetTimeEventTrigger implements EventTrigger {
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;
	@Override
	public void triggerEvent(EventParameters eventParameter) {
		if (eventParameter.getTime() != null && eventParameter.getLocation() != null) {
			GameObject gameObject = eventManager.getEventInfo().getGameObjectMap()
					.get(eventParameter.getLocation().getGameObjectPath());
			TargetTime targetTime = new TargetTime(eventParameter.getTime().getStartHour(), eventParameter.getTime()
					.getEndHour());
			gameObject.setTargetTime(targetTime);
		} else {
			Gdx.app.log("SetNpcTargetTimeEventTrigger", "eventParameter is null");
		}
		storySectionManager.runStorySequence();
	}
}
