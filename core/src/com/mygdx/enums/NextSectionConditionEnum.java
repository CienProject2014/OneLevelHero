package com.mygdx.enums;

public enum NextSectionConditionEnum {
	BATTLE("battle"), TIME("time"), MOVE("move"), SELECT("select"), SELECT_COMPONENT(
			"select_component");
	private String code;

	NextSectionConditionEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static NextSectionConditionEnum findNextSectionConditionEnum(
			String jsonName) {
		for (NextSectionConditionEnum nextSectionConditionEnum : NextSectionConditionEnum
				.values())
			if (nextSectionConditionEnum.toString().equals(jsonName))
				return nextSectionConditionEnum;

		return null;
	}
}
