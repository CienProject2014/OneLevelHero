package com.mygdx.model;

import com.mygdx.enums.EventTypeEnum;

public class StorySectionPacket {
	private int nextSectionNumber;
	private EventTypeEnum eventType;
	private String targetComponent;
	private String targetTime;

	public int getNextSectionNumber() {
		return nextSectionNumber;
	}

	public void setNextSectionNumber(int nextSectionNumber) {
		this.nextSectionNumber = nextSectionNumber;
	}

	public String getTargetComponent() {
		return targetComponent;
	}

	public void setTargetComponent(String targetComponent) {
		this.targetComponent = targetComponent;
	}

	public String getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

}
