package com.mygdx.enums;

public enum FieldTypeEnum {
	BLACKWOOD_MOUNTAIN("blackwood_mountain"), KADILLA_MOUNTAIN("kadilla_mountain"), ELVEN_FOREST(
			"elven_forest"), BLACKWOOD_FOREST("blackwood_forest"), BRIDGE("bridge"), GRASSLAND("grassland"), BEACH(
					"beach"), SEA("sea"), DUNGEON("dungeon");
	private String fieldType;
	private FieldTypeEnum(String fieldType) {
		this.fieldType = fieldType;
	}

	public String toString() {
		return fieldType;
	}
}
