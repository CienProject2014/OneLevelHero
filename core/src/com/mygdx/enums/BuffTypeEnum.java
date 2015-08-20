package com.mygdx.enums;

public enum BuffTypeEnum {
	BUFF("buff"), DEBUFF("debuff");
	private String buffType;

	BuffTypeEnum(String buffType) {
		this.buffType = buffType;
	}

	@Override
	public String toString() {
		return buffType;
	}
}
