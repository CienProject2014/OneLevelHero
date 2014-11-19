package com.mygdx.manager;

import com.mygdx.model.NPC;

public class EventManager {
	private static EventManager instance;
	private EventInfo eventInfo;

	private EventManager() {
		eventInfo = new EventInfo();
	}

	public class EventInfo {
		private NPC npc;
		private int eventNumber;
		private boolean greeting;

		public NPC getNpc() {
			return npc;
		}

		public void setNpc(NPC npc) {
			this.npc = npc;
		}

		public int getEventNumber() {
			return eventNumber;
		}

		public void setEventNumber(int eventNumber) {
			this.eventNumber = eventNumber;
		}

		public boolean isGreeting() {
			return greeting;
		}

		public void setGreeting(boolean greeting) {
			this.greeting = greeting;
		}
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
