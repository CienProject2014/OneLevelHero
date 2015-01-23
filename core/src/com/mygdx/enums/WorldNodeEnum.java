package com.mygdx.enums;

public enum WorldNodeEnum {
	BLACKWOOD("Blackwood");

	private String nodeName;

	WorldNodeEnum(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}
}
