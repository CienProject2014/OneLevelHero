package com.mygdx.enums;

public enum SkillTypeEnum {
	TECH("tech"), MAGIC("magic");

	private String code;

	SkillTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
