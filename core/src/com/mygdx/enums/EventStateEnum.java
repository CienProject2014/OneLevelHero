package com.mygdx.enums;

public enum EventStateEnum {
	NOT_OPENED("not_opend"), OPENED("opend"), ING("ing"), CLEARED("cleared");
	private String eventStateString;

	EventStateEnum(String eventStateString) {
		this.eventStateString = eventStateString;
	}

	@Override
	public String toString() {
		return eventStateString;
	}

	public static EventStateEnum findEventStateEnum(String stringName) {
		for (EventStateEnum eventStateEnum : EventStateEnum.values())
			if (eventStateEnum.toString().equals(stringName))
				return eventStateEnum;

		return null;
	}
}
