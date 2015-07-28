package com.mygdx.enums;

public enum PositionEnum {
	NODE("node"), SUB_NODE("sub_node"), FIELD("field");
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
