package com.mygdx.model.event;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class NPC extends EventElement {
	private Event greeting;
	private HashMap<Integer, Event> events;

	public Event getEvent(int eventNo) {
		try {
			return getEvents().get(String.valueOf(eventNo));
		} catch (IndexOutOfBoundsException e) {
			Gdx.app.error("error",
					String.format("eventNo %d not exist", eventNo));
			return null;
		}
	}

	public HashMap<Integer, Event> getEvents() {
		return events;
	}

	public void setEvents(HashMap<Integer, Event> events) {
		this.events = events;
	}

	public Event getGreeting() {
		return greeting;
	}

	public void setGreeting(Event greeting) {
		this.greeting = greeting;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result
				+ ((greeting == null) ? 0 : greeting.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NPC other = (NPC) obj;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (greeting == null) {
			if (other.greeting != null)
				return false;
		} else if (!greeting.equals(other.greeting))
			return false;
		return true;
	}
}
