package com.mygdx.enums;

public enum EventTypeEnum {
	GREETING("greeting"), CHAT("chat"), SELECT_EVENT("select_event"), CREDIT(
			"credit"), SELECT_COMPONENT("select_component"), MOVE("move");
	private String code;

	EventTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static EventTypeEnum findConditionTypeEnum(String code) {
		for (EventTypeEnum eventTypeEnum : EventTypeEnum.values())
			if (eventTypeEnum.toString().equals(code))
				return eventTypeEnum;
		return null;
	}
}
