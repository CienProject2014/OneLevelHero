package com.mygdx.enums;

public enum BattleMessages {
	MEET_MESSAGE("와 조우했다!"), PLAYER_WIN_MESSAGE("몬스터를 물리쳤다!");

	private String code;
	BattleMessages(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return this.code;
	}
}
