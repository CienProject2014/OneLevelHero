package com.mygdx.model;

import java.util.Iterator;
import java.util.List;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;

public class Event {
	private String eventName;
	private EventTypeEnum eventType;
	private EventStateEnum eventState;
	private RewardPack reward;
	private List<EventScene> eventScenes;
	private List<String> eventComponent;

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public RewardPack getReward() {
		return reward;
	}

	public void setReward(RewardPack reward) {
		this.reward = reward;
	}

	public List<EventScene> getEventScenes() {
		return eventScenes;
	}

	public void setEventScenes(List<EventScene> eventScenes) {
		this.eventScenes = eventScenes;
	}

	public Iterator<EventScene> getEventSceneIterator() {
		return eventScenes.iterator();
	}

	public EventStateEnum getEventState() {
		return eventState;
	}

	public void setEventState(EventStateEnum eventState) {
		this.eventState = eventState;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<String> getEventComponent() {
		return eventComponent;
	}

	public void setEventComponent(List<String> eventComponent) {
		this.eventComponent = eventComponent;
	}
}
