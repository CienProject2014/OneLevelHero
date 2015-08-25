package com.mygdx.currentState;

import com.badlogic.gdx.audio.Music;

public class MusicInfo {
	private transient Music music;
	public float musicVolume = 0.5f;

	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
