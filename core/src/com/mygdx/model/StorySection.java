package com.mygdx.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorySection {
	private String sectionName;
	private ArrayList<EventPacket> eventList;
	private HashMap<String, NextSectionCondition> nextSection;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public List<EventPacket> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<EventPacket> eventList) {
		this.eventList = eventList;
	}

	public HashMap<String, NextSectionCondition> getNextSection() {
		return nextSection;
	}

	public void setNextSection(HashMap<String, NextSectionCondition> nextSection) {
		this.nextSection = nextSection;
	}
}
