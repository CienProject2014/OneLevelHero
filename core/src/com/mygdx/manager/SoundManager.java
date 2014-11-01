package com.mygdx.manager;

import com.badlogic.gdx.audio.Music;

public class SoundManager {

	private static SoundManager instance;

	public SoundManager() {

	}

	public String getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(String currentSong) {
		this.currentSong = currentSong;
	}

	private String currentSong;

	public void playMusic(Music music) {
		music.play();
	}

	public static SoundManager getInstance() {
		if (null == instance) {
			instance = new SoundManager();
		}
		return instance;
	}

}
