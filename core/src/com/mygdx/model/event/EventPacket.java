package com.mygdx.model.event;

public class EventPacket {
	private int eventNumber;
	private String eventElement;

	public EventPacket() {

	}

	public EventPacket(String eventElement, int eventNumber) {
		this.eventElement = eventElement;
		this.eventNumber = eventNumber;
	}

	public int getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}
	public String getEventElement() {
		return eventElement;
	}

	public void setEventElement(String eventElement) {
		this.eventElement = eventElement;
	}

}
