package com.mygdx.model;

import java.util.Iterator;
import java.util.List;

import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;

public class Event {
	private EventTypeEnum eventType;
	private EventStateEnum eventState;
	private RewardInfo reward;
	private List<EventScene> eventScenes;
	private List<String> eventComponent;

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public RewardInfo getReward() {
		return reward;
	}

	public void setReward(RewardInfo reward) {
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

	public List<String> getEventComponent() {
		return eventComponent;
	}

	public void setEventComponent(List<String> eventComponent) {
		this.eventComponent = eventComponent;
	}
}
