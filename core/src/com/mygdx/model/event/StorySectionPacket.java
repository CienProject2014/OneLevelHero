package com.mygdx.model.event;

import com.mygdx.enums.EventTypeEnum;

public class StorySectionPacket {
	private int nextSectionNumber;
	private EventTypeEnum eventType;
	private EventParameters eventParameter;

	public int getNextSectionNumber() {
		return nextSectionNumber;
	}

	public void setNextSectionNumber(int nextSectionNumber) {
		this.nextSectionNumber = nextSectionNumber;
	}

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public EventParameters getEventParameter() {
		return eventParameter;
	}

	public void setEventParameter(EventParameters eventParameter) {
		this.eventParameter = eventParameter;
	}

}
