package com.mygdx.enums;

public enum UnitTextureEnum {
	ILLUST_NORMAL("101"), FACE_NORMAL("201"), FACE_HAPPY("202"), FACE_SMILE(
			"203"), FACE_ANGRY("204"), FACE_ANNOYED("205"), FACE_SICK("206"), FACE_SAD(
			"207"), BATTLE_ENEMY_NORMAL("301"), BATTLE_ENEMY_AWAKEN("302"), BATTLE_ENEMY_EXHAUSTED(
			"303"), BATTLE_ALLY_NORMAL("401"), BATTLE_ALLY_AWAKEN("402"), BATTLE_ALLY_EXHAUSTED(
			"403"), STATUS_NORMAL("501"), STATUS_AWAKEN("502"), NPC_NORMAL(
			"601"), NPC_AWAKEN("602");

	private String textureNumber;

	UnitTextureEnum(String textureNumber) {
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
