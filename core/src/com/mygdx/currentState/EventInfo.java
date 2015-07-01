package com.mygdx.currentState;

import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.Event;
import com.mygdx.model.NPC;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 *
 * @author Velmont
 *
 */

public class EventInfo {
	private NPC npc;
	private Event currentEvent;
	private boolean greeting;

	public NPC getNpc() {
		return npc;
	}

	public void setNpc(NPC npc) {
		this.npc = npc;
	}

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
}
