package com.mygdx.enums;

public enum PositionEnum {
	BATTLE("battle"), BATTLE_EVENT("battle_event"), NODE("node"), SUB_NODE(
			"sub_node"), FIELD("field"), NODE_EVENT("node_event"), SUB_NODE_EVENT(
			"sub_node_event"), FIELD_EVENT("field_event"), LOG("log");
	private String positionEnumString;

	PositionEnum(String positionEnumString) {
		this.positionEnumString = positionEnumString;
	}

	@Override
	public String toString() {
		return positionEnumString;
	}

	public static PositionEnum findPlaceEnum(String positionEnumString) {
		for (PositionEnum positionEnum : PositionEnum.values()) {
			if (positionEnum.toString().equals(positionEnumString))
				return positionEnum;
		}
		return null;
	}
}
