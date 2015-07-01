package com.mygdx.model;

import java.util.Map;

public class StoryQuarter {
	private String quarterName;
	private Map<NPC, String> eventNpc;
	private Map<GameObject, String> eventGameObject;
	private Map<StoryQuarter, NextQuarterCondition> nextQuarter;

	public String getQuarterName() {
		return quarterName;
	}

	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}

	public Map<StoryQuarter, NextQuarterCondition> getNextQuarter() {
		return nextQuarter;
	}

	public void setNextQuarter(
			Map<StoryQuarter, NextQuarterCondition> nextQuarter) {
		this.nextQuarter = nextQuarter;
	}

	public Map<NPC, String> getEventNpc() {
		return eventNpc;
	}

	public void setEventNpc(Map<NPC, String> eventNpc) {
		this.eventNpc = eventNpc;
	}

	public Map<GameObject, String> getEventGameObject() {
		return eventGameObject;
	}

	public void setEventGameObject(Map<GameObject, String> eventGameObject) {
		this.eventGameObject = eventGameObject;
	}

}
