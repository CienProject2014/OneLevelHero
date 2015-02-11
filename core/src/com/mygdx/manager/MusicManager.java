package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.audio.Music;
import com.mygdx.currentState.MusicInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.state.Assets;

public class MusicManager {
	@Autowired
	private MusicInfo musicInfo;
	@Autowired
	private Assets assets;
	@Autowired
	private PositionInfo positionInfo;

	public MusicInfo getMusicInfo() {
		return musicInfo;
	}

	public void setMusicInfo(MusicInfo musicInfo) {
		this.musicInfo = musicInfo;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public void playMusic(Music music) {
		musicInfo.getMusic().play();
	}

	public void setMusic(Music music) {
		musicInfo.setMusic(music);
	}

	public Music getMusic() {
		return musicInfo.getMusic();
	}

	public void setVolume(float volume) {
		musicInfo.getMusic().setVolume(volume);
	}

	public void playMusic() {
		musicInfo.getMusic().play();
	}

	public void stopMusic() {
		musicInfo.getMusic().stop();
	}

	public void setMusicAndPlay(Music music, float volume) {
		if (musicInfo.getMusic() != null)
			if (musicInfo.getMusic().isPlaying())
				stopMusic();
		setMusic(music);
		setVolume(volume);
		playMusic();
	}

	public void setMusicAndPlay(Music music) {
		setMusicAndPlay(music, assets.musicVolume);
	}

	public void setVillageMusicAndPlay() {
		Music music = assets.villageMusicMap.get(positionInfo.getCurrentNode());
		setMusicAndPlay(music);
	}
}
