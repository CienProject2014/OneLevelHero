package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class MoveFieldSectionChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args.length > 0) {
			String arrowName = eventParameter.getLocation().getArrowName();
			return ArgumentChecker.checkIsSame(arrowName, args[0]);
		} else {
			return false;
		}
	}
}
