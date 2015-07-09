package com.mygdx.enums;

public enum RewardTypeEnum {
	PARTY("party"), GOLD("gold"), EXPERIENCE("experience"), ITEM("item"), Reward(
			"Reward"), NONE("none"), EVENT("event");

	private String rewardTypeString;

	RewardTypeEnum(String rewardTypeString) {
		this.rewardTypeString = rewardTypeString;
	}

	@Override
	public String toString() {
		return rewardTypeString;
	}

	public static RewardTypeEnum findRewardTypeEnum(String stringName) {
		for (RewardTypeEnum rewardTypeEnum : RewardTypeEnum.values())
			if (rewardTypeEnum.toString().equals(stringName))
				return rewardTypeEnum;

		return null;
	}
}
