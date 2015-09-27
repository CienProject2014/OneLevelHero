package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class MoveSubNodeChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			if (args.length == 2) {
				String nodePath = eventParameter.getLocation().getNodePath();
				String subNodePath = eventParameter.getLocation().getSubNodePath();
				if (ArgumentChecker.checkIsSame(nodePath, args[0])) {
					return ArgumentChecker.checkIsSame(subNodePath, args[1]);
				}
			}
		}
		return false;
	}
}
