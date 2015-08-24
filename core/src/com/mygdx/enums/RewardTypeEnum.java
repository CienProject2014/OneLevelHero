package com.mygdx.enums;

public enum RewardTypeEnum {
	BATTLE("battle"), JOIN_PARTY("join_party"), LEAVE_PARTY("leave_party"), GOLD("gold"), EXPERIENCE("experience"), ITEM(
			"item"), NONE("none"), PASS_TIME("pass_time"), NEXT_SECTION("next_section"), SAVE("save"), REST_IN_NODE(
			"rest_in_node"), REST_IN_FORK("rest_in_fork"), MOVE_NODE("move_node");
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
