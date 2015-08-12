package com.mygdx.model.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;

public class Event {
	private String eventName;
	private EventTypeEnum eventType;
	private EventStateEnum eventState;
	private ArrayList<Reward> rewards;
	private ArrayList<EventScene> eventScenes;
	private ArrayList<String> eventComponent;
	private String eventTarget;

	public void setEventScenes(ArrayList<EventScene> eventScenes) {
		this.eventScenes = eventScenes;
	}

	public void setEventComponent(ArrayList<String> eventComponent) {
		this.eventComponent = eventComponent;
	}

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public List<EventScene> getEventScenes() {
		return eventScenes;
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventTarget() {
		return eventTarget;
	}

	public void setEventTarget(String eventTarget) {
		this.eventTarget = eventTarget;
	}

	public ArrayList<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(ArrayList<Reward> rewards) {
		this.rewards = rewards;
	}
}
