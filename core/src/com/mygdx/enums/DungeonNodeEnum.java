package com.mygdx.enums;

public enum DungeonNodeEnum {
	ENTRANCE("entrance"), ELITE("elite"), OBJECT("object"), BOSS("boss"), UP("up"), DOWN("down"), NORMAL("normal");
	private String code;

	private DungeonNodeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
