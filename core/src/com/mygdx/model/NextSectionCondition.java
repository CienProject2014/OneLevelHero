package com.mygdx.model;

import com.mygdx.enums.NextSectionConditionEnum;

/**
 * 다음 분기 조건을 다루는 모델 클래스
 * @author Velmont
 *
 */
public class NextSectionCondition {
	private NextSectionConditionEnum conditionType;
	private String target;
	private String time;

	public NextSectionConditionEnum getConditionType() {
		return conditionType;
	}

	public void setConditionType(NextSectionConditionEnum conditionType) {
		this.conditionType = conditionType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
