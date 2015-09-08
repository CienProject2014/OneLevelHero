package com.mygdx.model.event;

import java.util.ArrayList;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;

public class Event {
	private EventTypeEnum eventType;
	private EventStateEnum eventState;
	private EventParameters eventParameter;
	private String eventTitle;
	private ArrayList<Reward> rewards;

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public EventStateEnum getEventState() {
		return eventState;
	}

	public void setEventState(EventStateEnum eventState) {
		this.eventState = eventState;
	}

	public EventParameters getEventParameter() {
		return eventParameter;
	}

	public void setEventParameter(EventParameters eventParameter) {
		this.eventParameter = eventParameter;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public ArrayList<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(ArrayList<Reward> rewards) {
		this.rewards = rewards;
	}

}
