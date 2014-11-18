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

	public void setEventInfo(NPC npc, int eventNumber) {
		eventInfo.setNpc(npc);
		eventInfo.setEventNumber(eventNumber);
	}

}
