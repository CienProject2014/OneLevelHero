package com.mygdx.currentState;

import com.badlogic.gdx.audio.Music;

public class MusicInfo {
	private String preMusicName;
	private transient Music music;
	public float musicVolume = 0.5f;

	MusicInfo() {
		preMusicName = "";
	}

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

	public String getPreMusicName() {
		return preMusicName;
	}

	public void setPreMusicName(String preMusicName) {
		this.preMusicName = preMusicName;
	}
}
