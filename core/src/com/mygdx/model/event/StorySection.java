package com.mygdx.model.event;

import java.util.ArrayList;

public class StorySection {
	private ArrayList<EventPacket> sequencialEvents;
	private ArrayList<EventPacket> conditionalNpcEvents;
	private ArrayList<EventPacket> conditionalGameObjectEvents;
	private ArrayList<EventPacket> specialEvents;
	private ArrayList<StorySectionPacket> nextSections;

	public ArrayList<EventPacket> getSequencialEvents() {
		return sequencialEvents;
	}

	public void setSequencialEvents(ArrayList<EventPacket> sequencialEvents) {
		this.sequencialEvents = sequencialEvents;
	}

	public ArrayList<EventPacket> getConditionalNpcEvents() {
		return conditionalNpcEvents;
	}

	public void setConditionalNpcEvents(ArrayList<EventPacket> conditionalNpcEvents) {
		this.conditionalNpcEvents = conditionalNpcEvents;
	}

	public ArrayList<StorySectionPacket> getNextSections() {
		return nextSections;
	}

	public void setNextSections(ArrayList<StorySectionPacket> nextSections) {
		this.nextSections = nextSections;
	}

	public ArrayList<EventPacket> getSpecialEvents() {
		return specialEvents;
	}

	public void setSpecialEvents(ArrayList<EventPacket> specialEvents) {
		this.specialEvents = specialEvents;
	}

	public ArrayList<EventPacket> getConditionalGameObjectEvents() {
		return conditionalGameObjectEvents;
	}

	public void setConditionalGameObjectEvents(ArrayList<EventPacket> conditionalGameObjectEvent) {
		this.conditionalGameObjectEvents = conditionalGameObjectEvent;
	}

}