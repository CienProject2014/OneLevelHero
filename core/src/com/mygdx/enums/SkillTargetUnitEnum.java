package com.mygdx.enums;

public enum SkillTargetUnitEnum {
	MONSTER("monster"), SELF("self"), ALL("all"), ONE("one"), RANDOM("random");

	private String skillTargetType;

	SkillTargetUnitEnum(String skillTargetType) {
		this.skillTargetType = skillTargetType;
	}

	@Override
	public String toString() {
		return skillTargetType;
	}

	public static SkillTargetUnitEnum findSkillTargetEnum(String stringName) {
		for (SkillTargetUnitEnum skillTargetEnum : SkillTargetUnitEnum.values())
			if (skillTargetEnum.toString().equals(stringName))
				return skillTargetEnum;
		return null;
	}
}
