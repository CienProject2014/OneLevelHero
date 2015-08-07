package com.mygdx.model.event;

import com.mygdx.enums.GameObjectEnum;

public class GameObject extends EventElement {
	private GameObjectEnum objectType;
	private Event objectEvent;

	public Event getObjectEvent() {
		return objectEvent;
	}

	public void setObjectEvent(Event objectEvent) {
		this.objectEvent = objectEvent;
	}

	public GameObjectEnum getObjectType() {
		return objectType;
	}

	public void setObjectType(GameObjectEnum objectType) {
		this.objectType = objectType;
	}
}
