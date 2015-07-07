package com.mygdx.model;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.manager.JsonParser;

public class StorySection implements Serializable {
	private String sectionName;
	private List<EventPack> eventList;
	private Map<String, NextSectionCondition> nextSection;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public List<EventPack> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventPack> eventList) {
		this.eventList = eventList;
	}

	@Override
	public void write(Json json) {
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		sectionName = json.readValue("sectionName", String.class, jsonData);
		eventList = JsonParser.parseList(EventPack.class,
				jsonData.get("eventList").toString());
		nextSection = JsonParser.parseMap(NextSectionCondition.class, jsonData
				.get("nextSection").toString());
	}

	public Map<String, NextSectionCondition> getNextSection() {
		return nextSection;
	}

	public void setNextSection(Map<String, NextSectionCondition> nextSection) {
		this.nextSection = nextSection;
	}
}
