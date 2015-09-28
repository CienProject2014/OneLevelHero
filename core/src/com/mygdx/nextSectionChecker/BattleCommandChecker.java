package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class BattleCommandChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		return ArgumentChecker.checkIsSame(eventParameter.getBattle().getBattleCommand(), args[0]);
	}

}
