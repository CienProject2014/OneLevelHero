package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class BattleEndChecker implements NextSectionChecker {
	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args.length > 0) {
			return ArgumentChecker.checkIsSame(eventParameter.getBattle().getTargetMonster(), args[0]);
		} else {
			return false;
		}
	}
}
