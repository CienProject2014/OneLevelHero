package com.mygdx.model;

import com.mygdx.enums.NextSectionConditionEnum;

/**
 * 다음 분기 조건을 다루는 모델 클래스
 * @author Velmont
 *
 */
public class NextSectionPacket {
	private Integer nextSectionNumber;
	private NextSectionConditionEnum conditionType;
	private EventPacket targetEventInfo;
	private String targetComponent;
	private String targetTime;

	public Integer getNextSectionNumber() {
		return nextSectionNumber;
	}

	public void setNextSectionNumber(Integer nextSectionNumber) {
		this.nextSectionNumber = nextSectionNumber;
	}

	public NextSectionConditionEnum getConditionType() {
		return conditionType;
	}

	public void setConditionType(NextSectionConditionEnum conditionType) {
		this.conditionType = conditionType;
	}

	public String getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}

	public String getTargetComponent() {
		return targetComponent;
	}

	public void setTargetComponent(String targetComponent) {
		this.targetComponent = targetComponent;
	}

	public EventPacket getTargetEventInfo() {
		return targetEventInfo;
	}

	public void setTargetEventInfo(EventPacket targetEventInfo) {
		this.targetEventInfo = targetEventInfo;
	}

}
