package com.mygdx.util;

import com.mygdx.enums.EventTypeEnum;

public class EventManager {
	private String event;
	EventTypeEnum eventType;
	private String VillageCode;
	private String NPCCode;

	private String title;
	private String delimiter = "-";

	public void parseEvent(String event) {

	}

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
