package com.mygdx.nextSectionChecker;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.eventParameter.TimeParameter;

public class MoveSubNodeBeforeAbsoluteTimeChecker implements NextSectionChecker {
	@Autowired
	private TimeManager timeManager;
	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			String subNodePath = eventParameter.getLocation().getSubNodePath();
			if (ArgumentChecker.checkIsSame(subNodePath, args[0])) {
				TimeParameter currentTime = new TimeParameter(timeManager.getDay(), timeManager.getHour(),
						timeManager.getMinute());
				return ArgumentChecker.checkIsBeforeTime(currentTime, eventParameter.getTime());
			}
		}
		return false;
	}

}
