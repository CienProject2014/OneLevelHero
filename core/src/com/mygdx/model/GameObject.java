package com.mygdx.model;

public class GameObject extends EventElement {
	private String objectType;
	private Event objectEvent;

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Event getObjectEvent() {
		return objectEvent;
	}

	public void setObjectEvent(Event objectEvent) {
		this.objectEvent = objectEvent;
	}
}
