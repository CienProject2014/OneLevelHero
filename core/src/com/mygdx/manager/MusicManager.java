package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.assets.MusicAssets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.MusicInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.SoundInfo;

public class MusicManager {
	@Autowired
	private MusicInfo musicInfo;
	@Autowired
	private SoundInfo soundInfo;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private MusicAssets musicAssets;

	public enum MusicCondition {
		WHENEVER, IF_IS_NOT_PLAYING;
	}

	public MusicInfo getMusicInfo() {
		return musicInfo;
	}

	public void setMusicInfo(MusicInfo musicInfo) {
		this.musicInfo = musicInfo;
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

	public void setMusicAndPlay(Music music, float volume,
			MusicCondition musicCondition) {
		switch (musicCondition) {
			case WHENEVER:
				if (musicInfo.getMusic() != null)
					if (musicInfo.getMusic().isPlaying())
						stopMusic();
				int delayTime = 2000;
				Timer.schedule(new Task() {
					@Override
					public void run() {
					}
				}, delayTime);
				setMusic(music);
				setVolume(volume);
				playMusic();
				break;
			case IF_IS_NOT_PLAYING:
				if (musicInfo.getMusic() != null) {
					if (musicInfo.getMusic().isPlaying())
						return;
				} else {
					setMusic(music);
					setVolume(volume);
					playMusic();
				}
				break;
			default:
				Gdx.app.error("MusicManager", "incorrect musicCondition");
		}
	}

	public void setMusicAndPlay(Music music, MusicCondition musicCondition) {
		setMusicAndPlay(music, soundInfo.getMusicVolume(), musicCondition);
	}

	public void setMusicAndPlay(Music music, float volume) {
		setMusicAndPlay(music, volume, MusicCondition.WHENEVER);
	}

	public void setMusicAndPlay(Music music) {
		setMusicAndPlay(music, soundInfo.getMusicVolume());
	}

	public void setWorldNodeMusicAndPlay() {
		Music music = musicAssets.getWorldNodeMusic(positionInfo
				.getCurrentNode());
		setMusicAndPlay(music);
	}

	public void setBattleMusicAndPlay() {
		Music music = musicAssets.getBattleMusicMap(movingInfo.getArrowName());
		setMusicAndPlay(music);
	}

	public void setMovingMusicAndPlay() {
		Music music = musicAssets.getMovingMusicMap(movingInfo.getArrowName());
		setMusicAndPlay(music);
	}
}
