package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.StorySectionPacket;

public class EventCheckManager {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private StorySectionManager storySectionManager;

	public boolean checkSelectComponentEvent(int index,
			StorySectionPacket nextSectionPacket) {
		if (eventManager.getCurrentEvent().getEventComponent().get(index)
				.equals(nextSectionPacket.getTargetComponent())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkBattleEventType() {
		return eventManager.getCurrentEvent().getEventType()
				.equals(EventTypeEnum.BATTLE);
	}

	public boolean checkBattleControlEvent(
			StorySectionPacket nextSectionPacket, String controlName) {
		if (nextSectionPacket.getTargetComponent().equals(controlName)) {
			return true;
		} else {
			return false;
		}
	}
}
