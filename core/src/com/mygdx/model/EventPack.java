package com.mygdx.model;

import com.mygdx.enums.EventTypeEnum;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 *
 * @author Velmont
 *
 */

public class EventPack {
	private NPC currentNpc;
	private Event currentEvent;
	private boolean greeting;

	public boolean isGreeting() {
		return greeting;
	}

	public void setGreeting(boolean greeting) {
		this.greeting = greeting;
	}

	public EventTypeEnum getEventType() {
		if (isGreeting())
			return EventTypeEnum.SELECT_EVENT;
		return getCurrentEvent().getEventType();
	}

	public Event getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;
	}

	public NPC getCurrentNpc() {
		return currentNpc;
	}

	public void setCurrentNpc(NPC currentNpc) {
		this.currentNpc = currentNpc;
	}
}
