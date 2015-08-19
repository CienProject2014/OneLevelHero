package com.mygdx.enums;

public enum VillageDirectionEnum {
	UP_DOWN("up_down"), LEFT_RIGHT("left_right"), CENTER("center");
	private String code;

	private VillageDirectionEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
