package com.mygdx.model;

import java.util.List;

public class NPC extends Unit {
	private List<Event> event;
	private Event greeting;

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public Event getGreeting() {
		return greeting;
	}

	public void setGreeting(Event greeting) {
		this.greeting = greeting;
	}
}
