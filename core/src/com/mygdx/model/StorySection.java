package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

public class StorySection {
	private ArrayList<EventPacket> eventList;
	private ArrayList<NextSectionPacket> nextSectionList;

	public List<EventPacket> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<EventPacket> eventList) {
		this.eventList = eventList;
	}

	public ArrayList<NextSectionPacket> getNextSectionList() {
		return nextSectionList;
	}

	public void setNextSectionList(
			ArrayList<NextSectionPacket> nextSectionList) {
		this.nextSectionList = nextSectionList;
	}
}
