package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.assets.MusicAssets;
import com.mygdx.currentState.SoundInfo;

public class SoundManager {
	@Autowired
	private MusicAssets musicAssets;
	@Autowired
	private SoundInfo soundInfo;

	public void setSoundByUseAndPlay(String soundPath) {
		setSoundByUse(soundPath);
		if (soundInfo.getSound() != null) {
			playSound(getSoundVolume());
		}
	}

	public void playClickSound() {
		setSoundByPath("cursor");
		playSound(getSoundVolume());
	}

	public void setSoundByPathAndPlay(String soundPath) {
		setSoundByPath(soundPath);
		playSound(getSoundVolume());
	}

	public Sound getSound() {
		return soundInfo.getSound();
	}

	public void setSound(Sound sound) {
		soundInfo.setSound(sound);
	}

	public float getSoundVolume() {
		return soundInfo.getSoundVolume();
	}

	public void setSoundVolume(float soundVolume) {
		soundInfo.setSoundVolume(soundVolume);
	}

	private void setSoundByUse(String soundPath) {
		Sound sound = musicAssets.getSoundEffectByType(soundPath);
		soundInfo.setSound(sound);
	}

	private void playSound(float volume) {
		soundInfo.getSound().play(volume);
	}

	private void setSoundByPath(String soundPath) {
		Sound sound = musicAssets.getSound(soundPath);
		if (sound != null) {
			soundInfo.setSound(sound);
		} else {
			soundInfo.setSound(musicAssets.getSound("slash"));
		}
	}
}
