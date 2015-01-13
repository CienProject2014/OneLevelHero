package com.mygdx.enums;

public enum WorldNodeEnum {
	BLACKWOOD("blackwood");

	private String nodeName;

	WorldNodeEnum(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}
}
