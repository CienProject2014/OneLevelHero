package com.mygdx.enums;

public enum ItemTypeEnum {
	WEAPON("weapon"), CLOTHES("clothes"), ACCESSORY("accessoyr"), COMSUMABLES(
			"consumables"), ETC("etc");
	private String code;

	ItemTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
