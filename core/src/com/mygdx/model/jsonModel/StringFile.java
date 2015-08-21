package com.mygdx.model.jsonModel;

import com.badlogic.gdx.Gdx;
import com.mygdx.manager.AssetsManager;

public class StringFile implements AssetsFile<String> {
	private String filePath;
	private String file;

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

	@Override
	public String loadFile(AssetsManager assetsManager) {
		file = Gdx.files.internal(filePath).readString();
		return file;
	}
}
