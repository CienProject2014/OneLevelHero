package com.mygdx.enums;

public enum TextureEnum {
	BACKGROUND("bg"), FACE("face"), BUST("bust"), NPC("npc"), MONSTER("monster"), NORMAL(
			"01"), ATTACK_CUTTING("attack_cutting"), ATTACK_CUTTING2(
			"attack_cutting2"), BATTLE_TURN("battle"), STATUS("status"), BACKGROUND_UP(
			"background_up"), BACKGROUND_DOWN("background_down)");
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
