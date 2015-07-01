package com.mygdx.event;

import java.util.Vector;

import com.mygdx.event.listener.EventListener;
import com.mygdx.event.listener.ScreenChangeEventListener;

public class EventManager {
	private Vector<EventListener> listeners;
	private Vector<Event> events;

	public EventManager() {
		addListener(new ScreenChangeEventListener());

		// Event evt = new Event();
		// evt.info.put(ScreenChangeEvent.TAG_TYPE, ScreenChangeEvent.TYPE);
		// evt.info.put(ScreenChangeEvent.TAG_TARGET, ScreenEnum.BATTLE);
		// ==
		// Event evt = new ScreenChangeEvent(ScreenEnum.BATTLE);
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
