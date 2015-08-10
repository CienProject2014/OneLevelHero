package com.mygdx.enums;

public enum RewardTypeEnum {
	BATTLE("battle"), PARTY("party"), GOLD("gold"), EXPERIENCE("experience"), ITEM(
			"item"), NONE("none"), PASS_TIME("pass_time");
	private String code;

	RewardTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static RewardTypeEnum findRewardTypeEnum(String code) {
		for (RewardTypeEnum rewardTypeEnum : RewardTypeEnum.values())
			if (rewardTypeEnum.toString().equals(code))
				return rewardTypeEnum;
		return null;
	}
}
