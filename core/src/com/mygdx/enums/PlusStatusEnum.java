package com.mygdx.enums;

public enum PlusStatusEnum {
	PLUS("plus"), MINUS("minus");
	private String code;

	PlusStatusEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
