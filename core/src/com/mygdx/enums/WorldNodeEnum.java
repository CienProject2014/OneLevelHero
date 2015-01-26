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
}
