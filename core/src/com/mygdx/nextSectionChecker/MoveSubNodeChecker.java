package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class MoveSubNodeChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			if (args.length == 2) {
				String nodeName = eventParameter.getLocation().getNodeName();
				String subNodeName = eventParameter.getLocation().getSubNodeName();
				if (ArgumentChecker.checkIsSame(nodeName, args[0])) {
					return ArgumentChecker.checkIsSame(subNodeName, args[1]);
				}
			}
		}
		return false;
	}
}
