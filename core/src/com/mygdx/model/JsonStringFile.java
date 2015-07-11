package com.mygdx.model;

import com.badlogic.gdx.Gdx;

public class JsonStringFile implements AssetsFile<String> {
	private String filePath;
	private String file;

	@Override
	public String loadFile() {
		file = Gdx.files.internal(filePath).readString();
		return file;
	}

	@Override
	public String getTestFile() {
		file = Gdx.files.internal("../android/assets/" + filePath).readString();
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
