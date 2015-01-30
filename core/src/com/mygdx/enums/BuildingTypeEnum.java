package com.mygdx.enums;

public enum BuildingTypeEnum {
	INN("inn"), HOUSE("house");
	private String buildingTypeString;

	BuildingTypeEnum(String buildingType) {
		this.buildingTypeString = buildingType;
	}

	@Override
	public String toString() {
		return buildingTypeString;
	}
}
