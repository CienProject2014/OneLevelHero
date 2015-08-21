package com.mygdx.model.jsonModel;

import com.badlogic.gdx.audio.Music;
import com.mygdx.assets.StaticAssets;

public class MusicFile implements AssetsFile<String> {
	private String filePath;

	@Override
	public String loadFile() {
		StaticAssets.assetManager.load(filePath, Music.class);
		return filePath;
	}

	@Override
	public String getTestFile() {
		return null;
	}
}
