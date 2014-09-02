package com.mygdx.event;

import com.mygdx.enums.RewardTypeEnum;

public class RewardKey {
	private RewardTypeEnum keyOfRewardType;
	private String keyOfRewardValue;

	public String getKeyOfRewardValue() {
		return keyOfRewardValue;
	}

	public void setKeyOfRewardValue(String keyOfRewardValue) {
		this.keyOfRewardValue = keyOfRewardValue;
	}

	public RewardTypeEnum getKeyOfRewardType() {
		return keyOfRewardType;
	}

	public void setKeyOfRewardType(RewardTypeEnum keyOfRewardType) {
		this.keyOfRewardType = keyOfRewardType;
	}
}
