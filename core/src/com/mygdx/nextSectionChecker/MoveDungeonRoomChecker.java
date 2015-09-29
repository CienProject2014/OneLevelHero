package com.mygdx.nextSectionChecker;

import com.mygdx.model.event.EventParameters;

public class MoveDungeonRoomChecker implements NextSectionChecker {

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			String subNodePath = eventParameter.getLocation().getSubNodePath();
			String floorPath = eventParameter.getLocation().getFloorPath();
			String roomLabel = eventParameter.getLocation().getRoomLabel();
			if (ArgumentChecker.checkIsSame(subNodePath, args[0])) {
				if (ArgumentChecker.checkIsSame(floorPath, args[1])) {
					if (ArgumentChecker.checkIsSame(roomLabel, args[2])) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
