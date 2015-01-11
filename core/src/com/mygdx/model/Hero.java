package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.state.Assets;

public class Hero extends LivingUnit implements Fightable {
	private Equipment equipment;

	@Override
	public Texture getFaceTexture() {
		if (faceTexture == null)
			faceTexture = Assets.characterTextureMap.get(faceTexturePath);
		return faceTexture;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
