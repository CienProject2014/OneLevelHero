package com.mygdx.model.jsonModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.assets.StaticAssets;

public class MusicFile implements AssetsFile<Music> {
	private Music file;
	private String filePath;

	@Override
	public Music loadFile() {
		file = Gdx.audio.newMusic(Gdx.files.internal(filePath));
		StaticAssets.assetManager.load(filePath, Music.class);
		return file;
	}

	@Override
	public Music getTestFile() {
		return null;
	}
}
