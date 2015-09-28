package com.mygdx.enums;

public enum TextureEnum {
	BACKGROUND("bg"), GAME_OBJECT("object"), FACE("face"), BUST("bust"), NPC("npc"), MONSTER("monster"), NORMAL("01"), ATTACK_CUTTING(
			"attack_cutting"), ATTACK_CUTTING2("attack_cutting2"), BATTLE("battle"), STATUS("status"), ITEM("item"), BIG_IMAGE(
			"big"), SMALL_IMAGE("small"), MINIMAP("minimap");
	// FIXME 애니메이션 시트를 여기에 같이 두는게 적절한가?

	private String textureNumber;

	TextureEnum(String textureNumber) {
		this.setTextureNumber(textureNumber);
	}

	public String getTextureNumber() {
		return textureNumber;
	}

	public void setTextureNumber(String textureNumber) {
		this.textureNumber = textureNumber;
	}

	@Override
	public String toString() {
		return textureNumber;
	}
}
