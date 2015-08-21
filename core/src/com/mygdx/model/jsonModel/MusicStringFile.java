package com.mygdx.model.jsonModel;

import com.mygdx.manager.AssetsManager;

public class MusicStringFile implements AssetsFile<String> {
	private String file;
	private String filePath;

	@Override
	public String getTestFile() {
		return null;
	}

	@Override
	public String loadFile(AssetsManager assetsManager) {
		file = filePath;
		return file;
	}
}
