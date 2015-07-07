package com.mygdx.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.RewardTypeEnum;

public class Reward implements Serializable {
	private RewardTypeEnum rewardType;
	private String rewardTarget;
	private String rewardTargetAttribute;
	private RewardStateEnum rewardState;

	public String getRewardTarget() {
		return rewardTarget;
	}

	public void setRewardTarget(String rewardTarget) {
		this.rewardTarget = rewardTarget;
	}

	public RewardStateEnum getRewardState() {
		return rewardState;
	}

	public void setRewardState(RewardStateEnum rewardState) {
		this.rewardState = rewardState;
	}

	public RewardTypeEnum getRewardType() {
		return rewardType;
	}

	public void setRewardType(RewardTypeEnum rewardType) {
		this.rewardType = rewardType;
	}

	public String getRewardTargetAttribute() {
		return rewardTargetAttribute;
	}

	public void setRewardTargetAttribute(String rewardTargetAttribute) {
		this.rewardTargetAttribute = rewardTargetAttribute;
	}

	@Override
	public void write(Json json) {
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		rewardType = RewardTypeEnum.findRewardTypeEnum(json.readValue(
				"eventType", String.class, jsonData));
		rewardTarget = json.readValue("rewardTarget", String.class, jsonData);
		rewardTargetAttribute = json.readValue("rewardTargetAttribute",
				String.class, jsonData);
		rewardState = RewardStateEnum.findRewardStateEnum(json.readValue(
				"eventState", String.class, jsonData));
	}
}
