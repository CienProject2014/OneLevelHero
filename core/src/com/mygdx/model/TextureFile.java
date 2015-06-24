package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class TextureFile implements Disposable, AssetsFile<Texture> {
	private String filePath;
	private Texture file;

	@Override
	public Texture getFile() {
		file = new Texture(Gdx.files.internal(filePath));
		return file;
	}

	@Override
	public Texture getTestFile() {
		file = new Texture(Gdx.files.internal("../android/assets/" + filePath));
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

	@Override
	public void dispose() {
		Gdx.app.log("TextureFile", "Dispose");
		file.dispose();
	}
}
