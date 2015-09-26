package com.mygdx.enums;

public enum SkillEffectEnum {
	ATTACK("attack"), DUPLICATED_ATTACK("duplicated_attack"), ADD_STATE("add_state"), REMOVE_STATE(
			"remove_state"), CONDITIONAL_ATTACK("conditional_attack"), ADD_SELF_STATE("add_self_state"), CHANGE_GAUGE(
					"change_gauge"), MULTI_EFFECT("multi_effect"), HEAL("heal"), ALL_HEAL("all_heal");

	private String skillEffectType;

	SkillEffectEnum(String skillEffectType) {
		this.skillEffectType = skillEffectType;

	}

	@Override
	public String toString() {
		return skillEffectType;
	}

	public static SkillEffectEnum findSkillEffectEnum(String stringName) {
		for (SkillEffectEnum skillEffectEnum : SkillEffectEnum.values())
			if (skillEffectEnum.toString().equals(stringName))
				return skillEffectEnum;
		return null;
	}
}
