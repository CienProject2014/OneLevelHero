package com.mygdx.enums;

public enum NextSectionConditionEnum {
	BATTLE("battle"), TIME("time"), MOVE("move"), SELECT("select");
	private String nextSectionCondition;

	NextSectionConditionEnum(String nextSectionCondition) {
		this.nextSectionCondition = nextSectionCondition;
	}

	@Override
	public String toString() {
		return nextSectionCondition;
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
