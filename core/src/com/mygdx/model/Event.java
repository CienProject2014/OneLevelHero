package com.mygdx.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.JsonParser;

public class Event implements Serializable {
	private String eventName;
	private EventTypeEnum eventType;
	private EventStateEnum eventState;
	private Reward reward;
	private List<EventScene> eventScenes;
	private List<String> eventComponent;

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

	@Override
	public void write(Json json) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		eventName = json.readValue("eventName", String.class, jsonData);
		eventType = EventTypeEnum.findEventTypeEnum(json.readValue("eventType",
				String.class, jsonData));
		eventState = EventStateEnum.findEventStateEnum(json.readValue(
				"eventState", String.class, jsonData));
		reward = json.readValue("reward", Reward.class, jsonData);
		eventScenes = JsonParser.parseList(EventScene.class,
				jsonData.get("eventScenes").toString());
		eventComponent = json.readValue("eventComponent", ArrayList.class,
				String.class, jsonData);
	}
}
