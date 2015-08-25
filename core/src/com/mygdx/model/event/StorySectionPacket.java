package com.mygdx.model.event;

import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.surroundings.TargetTime;

public class StorySectionPacket {
	private int nextSectionNumber;
	private EventTypeEnum eventType;
	private String targetComponent;
	private TargetTime targetTime;

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

	public TargetTime getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(TargetTime targetTime) {
		this.targetTime = targetTime;
	}

	public String getTargetComponent() {
		return targetComponent;
	}

	public void setTargetComponent(String targetComponent) {
		this.targetComponent = targetComponent;
	}

}
