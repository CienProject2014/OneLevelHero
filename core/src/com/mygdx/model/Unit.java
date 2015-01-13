package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;

public class Unit {
	private String name;
	protected Texture faceTexture;
	protected String faceTexturePath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Texture getFaceTexture() {
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
