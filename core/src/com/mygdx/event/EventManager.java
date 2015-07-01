package com.mygdx.event;

import java.util.Vector;

import com.mygdx.event.listener.EventListener;

public class EventManager {
	private Vector<EventListener> listeners;
	private Vector<Event> events;

	public EventManager() {
	}

	public void update() {
		if (!isEmpty()) {
			for (EventListener listener : listeners)
				for (Event evt : events)
					listener.process(evt);
			events.clear();
		}
	}

	public void addListener(EventListener evtListener) {
		listeners.add(evtListener);
	}

	public void removeListener(EventListener evtListener) {
		listeners.remove(evtListener);
	}

	public void addEvent(Event evt) {
		events.add(evt);
	}

	public boolean isEmpty() {
		return events.isEmpty();
	}
}
