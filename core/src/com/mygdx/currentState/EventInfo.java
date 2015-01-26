package com.mygdx.currentState;

import org.springframework.stereotype.Component;

import com.mygdx.model.NPC;

@Component
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
