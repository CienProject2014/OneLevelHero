package com.mygdx.currentState;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.audio.Music;

@Component
public class MusicInfo {
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
