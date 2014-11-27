package com.mygdx.manager;

import com.mygdx.model.EventInfo;
import com.mygdx.model.NPC;

public class EventManager {
	private static EventManager instance;
	private EventInfo eventInfo;

	private EventManager() {
		eventInfo = new EventInfo();
	}

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(NPC npc, int eventNumber, boolean isGreeting) {
		eventInfo.setNpc(npc);
		eventInfo.setEventNumber(eventNumber);
		eventInfo.setGreeting(isGreeting);
	}

	public void setEventInfo(NPC npc, int eventNumber) {
		setEventInfo(npc, eventNumber, false);
	}

	public void setEventInfo(NPC npc, boolean isGreeting) {
		setEventInfo(npc, 0, isGreeting);
	}

}
