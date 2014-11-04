package com.mygdx.manager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.event.Event;
import com.mygdx.event.EventKey;
import com.mygdx.resource.Assets;
import com.mygdx.unit.NPC;

public class EventManager implements Manager {
	private String eventCode;
	private EventTypeEnum eventType;
	private static EventManager instance;
	private NPC eventNpc;
	private EventKey eventKey;

	public EventManager() {
		setEventCode("Prg-scene-1-F");
		parseEventCode(eventCode);
		setEventType(EventTypeEnum.CHAT);
	}

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
		parseEventCode(this.eventCode);
	}

	public EventKey getEventKey() {
		return eventKey;
	}

	public EventKey getEventKey(String eventCode) {
		this.eventCode = eventCode;
		parseEventCode(this.eventCode);
		return eventKey;
	}

	public EventManager setEventCode(String eventCode, EventTypeEnum eventType) {
		this.eventCode = eventCode;
		this.eventType = eventType;
		parseEventCode(this.eventCode);
		return this;
	}

	public String getEventVillageName() {
		String villageName[] = eventCode.split("-");
		return villageName[0];
	}

	public NPC getEventNpc() {
		return eventNpc;
	}

	public EventManager setNpcEvent() {
		Json json = new Json();
		Object jsonObject = Assets.waiji_json;
		eventNpc = new NPC();
		eventNpc.setNpcEvent(json.fromJson(Event.class, json.toJson(jsonObject)));
		return this;
	}

	private EventKey parseEventCode(String eventCode) {
		String[] temp = eventCode.split("-");
		eventKey = new EventKey();
		eventKey.setKeyOfVillage(temp[0]);
		eventKey.setKeyOfNpc(temp[1]);
		eventKey.setKeyOfSerialNumber(temp[2]);
		eventKey.setKeyOfReward(temp[3]);
		if (eventKey.getKeyOfReward().equals("T")) {
			String[] rewardCodeArray = eventNpc.getNpcEvent().getRewardCode();
			String rewardCode = rewardCodeArray[Integer.valueOf(eventKey.getKeyOfSerialNumber()) - 1];
			RewardManager.getInstance().setRewardCode(rewardCode);
		} else {
			RewardManager.getInstance().setRewardCode("NONE-none");
		}
		return eventKey;
	}


	@Override
	public Stage setStage(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
