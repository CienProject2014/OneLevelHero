package com.mygdx.enums;

public enum PlaceEnum {
	BUILDING("building"), FORK("fork"), VILLAGE("village"), MOVING("moving"), DUNGEON(
			"dungeon"), DUNGEON_ENTRANCE("dungeon_entrance");
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
