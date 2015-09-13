package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.NodeAssets;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.location.Building;
import com.mygdx.model.location.TargetTime;

public class SetSubNodeTargetTimeEventTrigger implements EventTrigger {
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		if (eventParameter.getTime() != null && eventParameter.getLocation() != null) {
			Building building = nodeAssets.getBuildingByPath(eventParameter.getLocation().getNodeName(), eventParameter
					.getLocation().getSubNodeName());
			TargetTime targetTime = new TargetTime(eventParameter.getTime().getStartHour(), eventParameter.getTime()
					.getEndHour());
			building.setTargetTime(targetTime);
			storySectionManager.runStorySequence();
		}
	}
}
