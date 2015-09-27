package com.mygdx.nextSectionChecker;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;

public class MoveNodeChecker implements NextSectionChecker {
	@Autowired
	private TimeManager timeManager;

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			if (args.length == 1) {
				String location = eventParameter.getLocation().getNodePath();
				return ArgumentChecker.checkIsSame(location, args[0]);
			} else if (args.length == 3) {
				String location = eventParameter.getLocation().getNodePath();
				int startHour = eventParameter.getTime().getStartHour();
				int endHour = eventParameter.getTime().getEndHour();
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
