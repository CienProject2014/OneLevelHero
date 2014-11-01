package com.mygdx.model;

import java.util.List;

import com.mygdx.enums.EventTypeEnum;

public class Event {
	private EventTypeEnum eventType;
	private Reward reward;
	private List<Stage> stage;

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

	public List<Stage> getScene() {
		return stage;
	}

	public void setScene(List<Stage> scene) {
		this.stage = scene;
	}

}
