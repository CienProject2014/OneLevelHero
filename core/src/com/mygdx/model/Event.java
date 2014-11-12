package com.mygdx.model;

import java.util.List;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;

public class Event {
	private EventTypeEnum eventType;
	private EventStateEnum eventState;
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

	public List<EventStage> getEventStage() {
		return eventStage;
	}

	public void setEventStage(List<EventStage> eventStage) {
		this.eventStage = eventStage;
	}

	public EventStateEnum getEventState() {
		return eventState;
	}

	public void setEventState(EventStateEnum eventState) {
		this.eventState = eventState;
	}

}
