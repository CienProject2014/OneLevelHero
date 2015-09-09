package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public interface NextSectionChecker {
	public boolean checkNextEvent(EventParameters eventParameter, String... args);
}
