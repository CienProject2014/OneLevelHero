package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.assets.MusicAssets;
import com.mygdx.currentState.SoundInfo;

public class SoundManager {
	@Autowired
	private MusicAssets musicAssets;
	private SoundInfo soundInfo;

	public SoundManager() {
		soundInfo = new SoundInfo();
	}

	public void setSoundByUseAndPlay(String soundPath) {
		setSoundByUse(soundPath);
		if (soundInfo.getSound() != null) {
			playSound(getSoundVolume());
		}
	}

	public void setSoundbyPathAndPlay(String soundPath) {
		setSoundByPath(soundPath);
		playSound(getSoundVolume());
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
		soundInfo.setSound(sound);
	}
}
