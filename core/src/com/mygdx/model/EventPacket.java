package com.mygdx.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.EventTypeEnum;
import com.mygdx.state.Assets;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 *
 * @author Velmont
 *
 */

public class EventPacket {
	@Autowired
	private Assets assets;

	private String eventNpc;
	private int eventNumber;
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
		return assets.npcMap.get(getEventNpc()).getEvent(getEventNumber())
				.getEventType();
	}

	public int getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getEventNpc() {
		return eventNpc;
	}

	public void setEventNpc(String eventNpc) {
		this.eventNpc = eventNpc;
	}
}
