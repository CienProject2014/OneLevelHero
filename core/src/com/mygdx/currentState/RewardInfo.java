package com.mygdx.currentState;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.RewardTypeEnum;

public class RewardInfo {
	private RewardTypeEnum rewardType;
	private String rewardTarget;
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
}
