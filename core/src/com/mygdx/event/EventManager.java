package com.mygdx.event;


public class EventManager {
	private static EventManager instance;

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

}
