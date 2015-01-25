package com.mygdx.manager;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.audio.Music;

@Component
public class SoundManager {

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
