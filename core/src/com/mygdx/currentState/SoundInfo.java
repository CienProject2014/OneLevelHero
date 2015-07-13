package com.mygdx.currentState;

import com.badlogic.gdx.audio.Music;

public class SoundInfo {
	public Music music, mainMusic;
	public float soundVolume = 0.5f;
	public float musicVolume = 0.5f;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public Music getMainMusic() {
		return mainMusic;
	}

	public void setMainMusic(Music mainMusic) {
		this.mainMusic = mainMusic;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}
}
