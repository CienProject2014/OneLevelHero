package com.mygdx.model;

import com.badlogic.gdx.Gdx;

public class JsonStringFile implements AssetsFile<String> {
	private String filePath;
	private String file;

	public String getFile() {
		file = Gdx.files.internal(filePath).readString();
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
