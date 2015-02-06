package com.mygdx.enums;

public enum WorldNodeEnum {
	BLACKWOOD("Blackwood");

	private String nodeName;

	WorldNodeEnum(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public String toString() {
		return nodeName;
	}

	public static WorldNodeEnum findWorldNodeEnum(String jsonName) {
		for (WorldNodeEnum worldNodeEnum : WorldNodeEnum.values()) {
			if (worldNodeEnum.toString().equals(jsonName))
				return worldNodeEnum;
		}
		return null;
	}
}
