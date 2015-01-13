package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.state.Assets;

public class Monster extends LivingUnit implements Fightable {
	@Override
	public Texture getFaceTexture() {
		if (faceTexture == null)
			faceTexture = Assets.monsterTextureMap.get(faceTexturePath);
		return faceTexture;
	}

}
