package com.mygdx.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.enums.NextSectionConditionEnum;

/**
 * 다음 분기 조건을 다루는 모델 클래스
 * @author Velmont
 *
 */
public class NextSectionCondition implements Serializable {
	private NextSectionConditionEnum conditionType;
	private Object target;
	private String time;

	public NextSectionConditionEnum getConditionType() {
		return conditionType;
	}

	public void setConditionType(NextSectionConditionEnum conditionType) {
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

	@Override
	public void write(Json json) {
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		conditionType = NextSectionConditionEnum
				.findNextSectionConditionEnum(json.readValue("conditionType",
						String.class, jsonData));
		target = json.readValue("target", String.class, jsonData);
	}

}
