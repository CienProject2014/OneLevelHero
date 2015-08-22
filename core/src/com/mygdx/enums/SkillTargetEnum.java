package com.mygdx.enums;

public enum SkillTargetEnum {
	MONSTER("monster"), SELF("self"), ALL("all"), ONE("one"), RANDOM("random");

	private String skillTargetType;

	SkillTargetEnum(String skillTargetType) {
		this.skillTargetType = skillTargetType;
	}

	@Override
	public String toString() {
		return skillTargetType;
	}

	public static SkillTargetEnum findSkillTargetEnum(String stringName) {
		for (SkillTargetEnum skillTargetEnum : SkillTargetEnum.values())
			if (skillTargetEnum.toString().equals(stringName))
				return skillTargetEnum;
		return null;
	}
}
