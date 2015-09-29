package com.mygdx.nextSectionChecker;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.EventParameters;

public class CollectEventChecker implements NextSectionChecker {
	@Autowired
	private EventManager eventManager;

	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		Iterator<EventPacket> eventIterator = eventParameter.getEvents().iterator();
		while (eventIterator.hasNext()) {
			EventPacket eventPacket = eventIterator.next();
			if (!eventManager.getEventInfo().getNpcMap().get(eventPacket.getEventElement())
					.getEvent(eventPacket.getEventNumber()).getEventState().equals(EventStateEnum.CLOSED)) {
				return false;
			}
		}
		return true;
	}
}
