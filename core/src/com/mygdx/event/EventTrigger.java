package com.mygdx.event;

import com.mygdx.util.EventManager;

public class EventTrigger {

	public EventTrigger() {
	}

	private boolean event;

	public boolean hasEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public void makeEvent(String eventCode) {
		EventManager.getInstance().setEventCode(eventCode);
	}
}
