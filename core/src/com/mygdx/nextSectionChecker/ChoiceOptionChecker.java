package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class ChoiceOptionChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args.length > 0) {
			String targetOption = eventParameter.getTargetComponent();
			if (ArgumentChecker.checkIsSame(targetOption, args[0])) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
}
