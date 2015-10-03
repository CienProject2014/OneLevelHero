package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class DoNothingChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		return false;
	}

}
