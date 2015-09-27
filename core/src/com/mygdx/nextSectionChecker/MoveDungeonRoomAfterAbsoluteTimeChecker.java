package com.mygdx.nextSectionChecker;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.eventParameter.TimeParameter;

public class MoveDungeonRoomAfterAbsoluteTimeChecker implements NextSectionChecker {
	@Autowired
	private TimeManager timeManager;
	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			String subNodePath = eventParameter.getLocation().getSubNodePath();
			String floorPath = eventParameter.getLocation().getFloorPath();
			String roomLabel = eventParameter.getLocation().getRoomLabel();
			if (ArgumentChecker.checkIsSame(subNodePath, args[0])) {
				if (ArgumentChecker.checkIsSame(floorPath, args[1])) {
					if (ArgumentChecker.checkIsSame(roomLabel, args[2])) {
						TimeParameter currentTime = new TimeParameter(timeManager.getDay(), timeManager.getHour(),
								timeManager.getMinute());
						return ArgumentChecker.checkIsAfterTime(currentTime, eventParameter.getTime());
					}
				}
			}
		}
		return false;
	}
}
