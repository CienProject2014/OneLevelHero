package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.state.Assets;

public class Unit {
	private String name;
	private Texture faceTexture;
	private String faceTexturePath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Texture getFaceTexture() {
		if (faceTexture == null)
			faceTexture = Assets.imageFileMap.get(faceTexturePath);
		return faceTexture;
	}

	public void setFaceTexture(Texture faceTexture) {
		this.faceTexture = faceTexture;
	}

	public String getFaceTexturePath() {
		return faceTexturePath;
	}

	public void setFaceTexturePath(String faceTexturePath) {
		this.faceTexturePath = faceTexturePath;
	}
}
