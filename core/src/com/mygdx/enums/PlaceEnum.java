package com.mygdx.enums;

public enum PlaceEnum {
	BUILDING("building"), FORK("fork"), VILLAGE("village"), DUNGEON("dungeon");
	private String placeEnumString;

	PlaceEnum(String placeEnumString) {
		this.placeEnumString = placeEnumString;
	}

	@Override
	public String toString() {
		return placeEnumString;
	}

	public static PlaceEnum findPlaceEnum(String placeEnumString) {
		for (PlaceEnum placeEnum : PlaceEnum.values()) {
			if (placeEnum.toString().equals(placeEnumString))
				return placeEnum;
		}
		return null;
	}
}
