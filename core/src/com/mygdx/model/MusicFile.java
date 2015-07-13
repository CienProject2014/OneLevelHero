package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicFile implements AssetsFile<Music> {
	private Music file;
	private String filePath;

	@Override
	public Music loadFile() {
		file = Gdx.audio.newMusic(Gdx.files.internal(filePath));
		return file;
	}

	@Override
	public Music getTestFile() {
		return null;
	}
}
