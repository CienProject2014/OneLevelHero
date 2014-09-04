package com.mygdx.unit;

import java.util.ArrayList;

import com.mygdx.event.Event;

public class NPC {
	private Event npcEvent;
	public ArrayList<Boolean> finishEventList;
	public ArrayList<Boolean> finishRewardList;

	public NPC() {
		finishEventList = new ArrayList<Boolean>();
		finishRewardList = new ArrayList<Boolean>();
		for (int i = 0; i < 6; i++) {
			finishEventList.add(false);
			finishRewardList.add(false);
		}
	}

	public Event getNpcEvent() {
		return npcEvent;
	}

	public void setNpcEvent(Event event) {
		this.npcEvent = event;
	}

}
