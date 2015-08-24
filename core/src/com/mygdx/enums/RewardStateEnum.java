package com.mygdx.enums;

public enum RewardStateEnum {
	NOT_CLEARED("not_cleared"), ING("ing"), CLEARED("cleared"), ALWAYS_OPEN("always_open");
	private String rewardState;

	private RewardStateEnum(String rewardState) {
		this.rewardState = rewardState;
	}

	@Override
	public String toString() {
		return rewardState;
	}
}
