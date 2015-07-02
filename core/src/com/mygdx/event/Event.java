package com.mygdx.event;

import java.util.Map;

public class Event {
	public Map<String, Object> info;

	public Event() {
	}

	public Event(Map<String, Object> info) {
		this.info = info;
	}
}
