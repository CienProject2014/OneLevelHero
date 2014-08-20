package com.mygdx.event;

public enum EventTypeEnum {
	CHAT("chat");
	String textureRegion;

	private EventTypeEnum(String textureRegion) {
		this.textureRegion = textureRegion;
	}

	public String getTextureRegion() {
		return textureRegion;
	}
}
