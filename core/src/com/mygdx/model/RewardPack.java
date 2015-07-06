package com.mygdx.model;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.RewardTypeEnum;

public class RewardPack {
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
}
