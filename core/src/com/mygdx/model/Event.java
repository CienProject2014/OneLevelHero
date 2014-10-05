package com.mygdx.model;

import java.util.List;

public class Event {
	private String eventType;
	private Reward reward;
	private List<Scene> scene;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public List<Scene> getScene() {
		return scene;
	}

	public void setScene(List<Scene> scene) {
		this.scene = scene;
	}

}
