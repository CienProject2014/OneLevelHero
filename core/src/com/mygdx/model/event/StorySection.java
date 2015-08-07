package com.mygdx.model.event;

import java.util.ArrayList;


public class StorySection {
	private ArrayList<EventPacket> sequencialEvents;
	private ArrayList<EventPacket> conditionalEvents;
	private ArrayList<StorySectionPacket> nextSections;

	public ArrayList<EventPacket> getSequencialEvents() {
		return sequencialEvents;
	}

	public void setSequencialEvents(ArrayList<EventPacket> sequencialEvents) {
		this.sequencialEvents = sequencialEvents;
	}

	public ArrayList<EventPacket> getConditionalEvents() {
		return conditionalEvents;
	}

	public void setConditionalEvents(ArrayList<EventPacket> conditionalEvents) {
		this.conditionalEvents = conditionalEvents;
	}

	public ArrayList<StorySectionPacket> getNextSections() {
		return nextSections;
	}

	public void setNextSections(ArrayList<StorySectionPacket> nextSections) {
		this.nextSections = nextSections;
	}

}