package com.mygdx.event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mygdx.event.listener.OneLevelEventListener;
import com.mygdx.event.listener.ScreenChangeEventListener;

public class EventManager {
	private List<OneLevelEventListener> listeners;
	private List<Event> events;

	public EventManager() {
		addListener(new ScreenChangeEventListener());
		listeners = new LinkedList<>();
		events = new ArrayList<>();
	}

	public void update() {
		if (!isEmpty()) {
			for (OneLevelEventListener listener : listeners)
				for (Event evt : events)
					listener.process(evt);
			events.clear();
		}
	}

	public void addListener(OneLevelEventListener eventListener) {
		listeners.add(eventListener);
	}

	public void removeListener(OneLevelEventListener evtListener) {
		listeners.remove(evtListener);
	}

	public void addEvent(Event evt) {
		events.add(evt);
	}

	public boolean isEmpty() {
		return events.isEmpty();
	}
}
