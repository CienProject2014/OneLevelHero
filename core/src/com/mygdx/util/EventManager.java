package com.mygdx.util;

import com.mygdx.enums.EventTypeEnum;
import com.mygdx.unit.NPC;

public class EventManager {
	private String eventCode;
	EventTypeEnum eventType;
	private String title;
	private static EventManager instance;
	private NPC eventNpc;

	public EventManager() {
		setEventCode("Prg-scene-1");
		setEventType(EventTypeEnum.CHAT);
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

	public void setEventCode(String eventCode, EventTypeEnum eventType) {
		this.eventCode = eventCode;
		this.eventType = eventType;
	}

	public String getEventVillageName() {
		String villageName[] = eventCode.split("-");
		return villageName[0];
	}

	public NPC getEventNpc() {
		return eventNpc;
	}

	public void setEventNpc(NPC eventNpc) {
		this.eventNpc = eventNpc;
	}
}
