package com.mygdx.model;

import com.mygdx.enums.RewardStateEnum;

public class Reward {
	private String rewardType;
	private String rewardTarget;
	private RewardStateEnum rewardState;

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

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
}
