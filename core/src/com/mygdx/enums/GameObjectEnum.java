package com.mygdx.enums;

public enum GameObjectEnum {
	PRESSED("pressed"), NORMAL("normal");
	
	private String code;
	private GameObjectEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
