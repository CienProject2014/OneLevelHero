package com.mygdx.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.state.Assets;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 *
 * @author Velmont
 *
 */

public class EventPack implements Serializable {
	@Autowired
	private Assets assets;

	private String eventNpc;
	private int eventNumber;
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

	public int getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}

	@Override
	public void write(Json json) {
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		eventNpc = json.readValue("eventNpc", String.class, jsonData);
		eventNumber = json.readValue("eventNumber", Integer.class, jsonData);
	}

	public String getEventNpc() {
		return eventNpc;
	}

	public void setEventNpc(String eventNpc) {
		this.eventNpc = eventNpc;
	}
}
