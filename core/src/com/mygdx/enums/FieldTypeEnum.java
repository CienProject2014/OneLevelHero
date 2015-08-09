package com.mygdx.enums;

public enum FieldTypeEnum {
	MOUNTAIN("산"), FOREST("숲"), BRIDGE("다리"), GRASSLAND("초원"), BEACH("해변"), SEA(
			"바다");
	private String fieldType;
	private FieldTypeEnum(String fieldType) {
		this.fieldType = fieldType;
	}

	public String toString() {
		return fieldType;
	}
}
