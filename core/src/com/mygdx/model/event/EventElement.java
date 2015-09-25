package com.mygdx.model.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.mygdx.manager.EventManager;
import com.mygdx.model.location.TargetTime;

public class EventElement {
	protected int positionIndex;
	protected HashMap<String, Event> events;
	protected TargetTime targetTime;
	protected String name;
	protected String facePath;
	protected List<String> greetingMessages;

	public int getPositionIndex() {
		return positionIndex;
	}

	public void setPositionIndex(int positionIndex) {
		this.positionIndex = positionIndex;
	}

	public Event getEvent(int eventNo) {
		try {
			return getEvents().get(String.valueOf(eventNo));
		} catch (IndexOutOfBoundsException e) {
			Gdx.app.error("error", String.format("eventNo %d not exist", eventNo));
			return null;
		}
	}

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGreetingMessages() {
		return greetingMessages;
	}

	public void setGreetingMessages(List<String> greetingMessages) {
		this.greetingMessages = greetingMessages;
	}

	public TargetTime getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(TargetTime targetTime) {
		this.targetTime = targetTime;
	}

	public HashMap<String, Event> getEvents() {
		return events;
	}

	public void setEvents(HashMap<String, Event> events) {
		this.events = events;
	}

	public int getVisibleEventSize() {
		int count = 0;
		Iterator<Entry<String, Event>> eventsIterator = events.entrySet().iterator();
		while (eventsIterator.hasNext()) {
			Event nextEvent = eventsIterator.next().getValue();
			if (EventManager.isEventVisible(nextEvent)) {
				count++;
			}
		}
		return count;
	}
}
