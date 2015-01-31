package com.mygdx.enums;

public enum MinimapEnum {
	O_WAY("O"), X_WAY("X"), MONSTER_WAY("M");

	private String minimapString;

	MinimapEnum(String minimapString) {
		this.minimapString = minimapString;
	}

	@Override
	public String toString() {
		return minimapString;
	}
}
