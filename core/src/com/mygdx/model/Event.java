package com.mygdx.model;

import java.util.List;

import com.mygdx.enums.EventTypeEnum;

public class Event {
	private EventTypeEnum eventType;
	private Reward reward;
	private List<EventStage> eventStage;

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public List<EventStage> getScene() {
		return eventStage;
	}

	public void setScene(List<EventStage> scene) {
		this.eventStage = scene;
	}

}
