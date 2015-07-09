package com.mygdx.enums;

public enum PlaceEnum {
	BUILDING("building"), FORK("fork"), VILLAGE("village"), DUNGEON("dungeon");
	private String code;

	PlaceEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static PlaceEnum findPlaceEnum(String code) {
		for (PlaceEnum placeEnum : PlaceEnum.values()) {
			if (placeEnum.toString().equals(code))
				return placeEnum;
		}
		return null;
	}
}
