package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureFile implements AssetsFile<Texture> {
	private String filePath;
	private Texture file;

	@Override
	public Texture getFile() {
		file = new Texture(Gdx.files.internal(filePath));
		return file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFile(Texture file) {
		this.file = file;
	}
}