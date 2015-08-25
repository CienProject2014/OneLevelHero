package com.mygdx.currentState;

import com.badlogic.gdx.audio.Sound;

public class SoundInfo {
	public Sound sound;
	public float soundVolume = 0.5f;

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
}
