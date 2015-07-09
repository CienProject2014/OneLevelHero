package com.mygdx.enums;

public enum RewardStateEnum {
	NOT_CLEARED("not_cleared"), ING("ing"), CLEARED("cleared"), NO_ISSUE(
			"no_issue");

	private String rewardStateString;

	RewardStateEnum(String rewardStateString) {
		this.rewardStateString = rewardStateString;
	}

	@Override
	public String toString() {
		return rewardStateString;
	}

	public static RewardStateEnum findRewardStateEnum(String stringName) {
		for (RewardStateEnum rewardStateEnum : RewardStateEnum.values())
			if (rewardStateEnum.toString().equals(stringName))
				return rewardStateEnum;

		return null;
	}
}
