package com.mygdx.model;

import com.mygdx.enums.NextQuarterConditionEnum;

public class NextQuarterCondition {
	private NextQuarterConditionEnum conditionType;
	private Object target;
	private String time;

	public NextQuarterConditionEnum getConditionType() {
		return conditionType;
	}

	public void setConditionType(NextQuarterConditionEnum conditionType) {
		this.conditionType = conditionType;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
