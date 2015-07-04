package com.mygdx.enums;

public enum TextureEnum {
	ILLUST_NORMAL("101"), FACE_NORMAL("201"), FACE_HAPPY("202"), FACE_SMILE(
			"203"), FACE_ANGRY("204"), FACE_ANNOYED("205"), FACE_SICK("206"), FACE_SAD(
			"207"), BATTLE_ENEMY_NORMAL("351"), BATTLE_ENEMY_AWAKEN("352"), BATTLE_ENEMY_EXHAUSTED(
			"353"), BATTLE_ALLY_NORMAL("301"), BATTLE_ALLY_AWAKEN("302"), BATTLE_ALLY_EXHAUSTED(
			"303"), STATUS_NORMAL("401"), STATUS_AWAKEN("402"), NPC_NORMAL(
			"501"), NPC_AWAKEN("502"), BACKGROUND_UP("701"), BACKGROUND_DOWN(
			"702"), ATTACK_CUTTING("attack_cutting"), ATTACK_CUTTING2(
			"attack_cutting2");
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
