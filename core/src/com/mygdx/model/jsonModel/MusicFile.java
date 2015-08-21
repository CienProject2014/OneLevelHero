package com.mygdx.model.jsonModel;

import com.badlogic.gdx.audio.Music;
import com.mygdx.manager.AssetsManager;

public class MusicFile implements AssetsFile<String> {
	private String filePath;

	@Override
	public String loadFile(AssetsManager assetsManager) {
		assetsManager.load(filePath, Music.class);
		return filePath;
	}

	@Override
	public String getTestFile() {
		return null;
	}
}
