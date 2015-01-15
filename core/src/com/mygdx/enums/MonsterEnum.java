package com.mygdx.enums;

public enum MonsterEnum {
	ASSASSIN_GIRL("assassin girl");

	private String monsterName;

	MonsterEnum(String monsterName) {
		this.monsterName = monsterName;
	}

	@Override
	public String toString() {
		return monsterName;
	}
}
