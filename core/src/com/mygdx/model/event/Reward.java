package com.mygdx.model.event;

import java.util.List;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.RewardTypeEnum;

public class Reward {
	private RewardTypeEnum rewardType;
	private RewardStateEnum rewardState;
	private List<String> rewardComponent;

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

	public List<String> getRewardComponent() {
		return rewardComponent;
	}

	public void setRewardComponent(List<String> rewardComponent) {
		this.rewardComponent = rewardComponent;
	}
}
