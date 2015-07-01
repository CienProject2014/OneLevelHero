package com.mygdx.enums;

public enum NextQuarterConditionEnum {
	BATTLE("battle"), TIME("time"), MOVE("move"), SELECT("select");
	private String nextQuarterCondition;

	NextQuarterConditionEnum(String nextQuarterCondition) {
		this.nextQuarterCondition = nextQuarterCondition;
	}

	@Override
	public String toString() {
		return nextQuarterCondition;
	}
}
