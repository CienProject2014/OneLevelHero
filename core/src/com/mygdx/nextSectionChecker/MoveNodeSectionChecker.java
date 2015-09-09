package com.mygdx.nextSectionChecker;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;

public class MoveNodeSectionChecker implements NextSectionChecker {
	@Autowired
	private TimeManager timeManager;

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			if (args.length == 1) {
				String location = eventParameter.getLocation().getNodeName();
				return ArgumentChecker.checkIsSame(location, args[0]);
			} else if (args.length == 3) {
				String location = eventParameter.getLocation().getNodeName();
				int startHour = eventParameter.getTargetTime().getStartHour();
				int endHour = eventParameter.getTargetTime().getEndHour();
				int currentMinute = timeManager.getDayMinute();
				return ArgumentChecker.checkIsSameLocationInTargetTime(location, startHour, endHour, currentMinute,
						args);
			} else if (args.length == 4) {
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
