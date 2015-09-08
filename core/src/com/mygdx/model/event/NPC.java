package com.mygdx.model.event;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class NPC extends EventElement {
	private HashMap<String, Event> events;

	public Event getEvent(int eventNo) {
		try {
			return getEvents().get(String.valueOf(eventNo));
		} catch (IndexOutOfBoundsException e) {
			Gdx.app.error("error", String.format("eventNo %d not exist", eventNo));
			return null;
		}
	}

	public HashMap<String, Event> getEvents() {
		return events;
	}

	public void setEvents(HashMap<String, Event> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		return result;
	}
}
