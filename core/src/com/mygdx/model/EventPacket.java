package com.mygdx.model;

public class EventPacket {
	private int eventNumber;
	private String eventNpc;
	private boolean greeting;

	public int getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getEventNpc() {
		return eventNpc;
	}

	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}

	public void setEventNpc(String eventNpc) {
		this.eventNpc = eventNpc;
	}

	public boolean isGreeting() {
		return greeting;
	}

	public void setGreeting(boolean greeting) {
		this.greeting = greeting;
	}

}
