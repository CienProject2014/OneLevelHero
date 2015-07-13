package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

public class EventElement {
	protected String name;
	protected ArrayList<Event> events;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public List<Event> getEvents() {
		return events;
	}

}
