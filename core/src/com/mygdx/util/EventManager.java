package com.mygdx.util;

import com.mygdx.enums.EventTypeEnum;

public class EventManager {
	private String eventCode;
	EventTypeEnum eventType;
	private String title;
	private static EventManager instance;

	public EventManager() {
		setEventCode("Prologue-scene-1");
	}

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

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

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventVillageName() {
		String villageName[] = eventCode.split("-");
		return villageName[0];
	}

}
