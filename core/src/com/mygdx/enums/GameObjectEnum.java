package com.mygdx.enums;

public enum GameObjectEnum {
	PRESSED("pressed"), NORMAL("normal");

	private String code;

	private GameObjectEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
