package com.mygdx.util;

import com.badlogic.gdx.audio.Music;

public class SoundManager {
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

}
