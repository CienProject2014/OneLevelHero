package com.mygdx.enums;

public enum EventTypeEnum {
	GREETING("greeting"), BATTLE("battle"), CHAT("chat"), SELECT_EVENT(
			"select_event"), CREDIT("credit"), SELECT_COMPONENT(
			"select_component"), MOVE("move"), BATTLE_CONTROL("battle_control"), MUSIC(
			"music");
	private String code;

	EventTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static EventTypeEnum findEventTypeEnum(String code) {
		for (EventTypeEnum eventTypeEnum : EventTypeEnum.values())
			if (eventTypeEnum.toString().equals(code))
				return eventTypeEnum;
		return null;
	}
}
