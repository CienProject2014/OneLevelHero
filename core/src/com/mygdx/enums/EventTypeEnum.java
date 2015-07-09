package com.mygdx.enums;

public enum EventTypeEnum {
	CHAT("chat"), MOVE("move"), SELECT_EVENT("select_event"), SELECT_COMPONENT(
			"select_component"), CREDIT("credit");
	private String eventTypeString;

	EventTypeEnum(String eventTypeString) {
		this.eventTypeString = eventTypeString;
	}

	@Override
	public String toString() {
		return eventTypeString;
	}

	public static EventTypeEnum findEventTypeEnum(String stringName) {
		for (EventTypeEnum eventTypeEnum : EventTypeEnum.values())
			if (eventTypeEnum.toString().equals(stringName))
				return eventTypeEnum;

		return null;
	}
}
