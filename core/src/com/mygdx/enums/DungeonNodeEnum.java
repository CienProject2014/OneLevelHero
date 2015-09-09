package com.mygdx.enums;

public enum DungeonNodeEnum {
	GATE("gate"), ELITE("elite"), OBJECT("object"), BOSS("boss"), UP_STAIR("up_stair"), DOWN("down_stair"), NORMAL(
			"normal");
	private String code;

	private DungeonNodeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
