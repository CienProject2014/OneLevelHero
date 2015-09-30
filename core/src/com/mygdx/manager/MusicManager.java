package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.assets.MusicAssets;
import com.mygdx.currentState.MusicInfo;
import com.mygdx.enums.MusicEnum;

public class MusicManager {
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private MusicAssets musicAssets;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private MusicInfo musicInfo;

	public enum MusicCondition {
		WHENEVER, IF_IS_NOT_PLAYING;
	}

	public float getMusicVolume() {
		return musicInfo.getMusicVolume();
	}

	public void playMusic(Music music) {
		music.setVolume(getMusicVolume());
		musicInfo.setMusic(music);
		playMusic();
	}

	public void playMusic(String musicName) {
		musicInfo.setPreMusicName(musicName);
		Music music = musicAssets.getMusic(musicName);
		music.setVolume(getMusicVolume());
		musicInfo.setMusic(music);
		playMusic();
	}

	public Music getMusic() {
		return musicInfo.getMusic();
	}

	public void setMusicVolume(float volume) {
		musicInfo.setMusicVolume(volume);
	}

	public void playMusic() {
		musicInfo.getMusic().setLooping(true);
		musicInfo.getMusic().play();
	}

	public void stopMusic() {
		musicInfo.getMusic().stop();
	}

	public void setMusicAndPlay(String musicName, MusicEnum musicType, MusicCondition musicCondition) {
		switch (musicCondition) {
			case WHENEVER :
				int delayTime = 2000;
				if (checkCurrentMusicIsNotNull()) {
					if (!checkIsSameWithCurrentMusic(musicName, musicType)) {
						stopMusic();
						Timer.schedule(new Task() {
							@Override
							public void run() {
							}
						}, delayTime);
						setMusicByType(musicName, musicType);
						playMusic();
					}
				} else {
					setMusicByType(musicName, musicType);
					playMusic();
				}
				break;
			case IF_IS_NOT_PLAYING :
				if (checkCurrentMusicIsNotNull()) {
					if (checkCurrentMusicIsPlaying())
						return;
				} else {
					setMusicByType(musicName, musicType);
					playMusic();
				}
				break;
			default :
				Gdx.app.error("MusicManager", "incorrect musicCondition");
		}
	}

	private void setMusicByType(String musicName, MusicEnum musicType) {
		Music music;
		switch (musicType) {
			case NORMAL :
				setMusic(musicName);
				break;
			case WORLD_NODE_MUSIC :
				String currentNode = positionManager.getCurrentNodePath();
				music = musicAssets.getWorldNodeMusic(currentNode);
				musicInfo.setPreMusicName(currentNode);
				music.setVolume(musicInfo.getMusicVolume());
				musicInfo.setMusic(music);
				break;
			case BATTLE_MUSIC :
				music = musicAssets.getBattleMusic("bgm_battle");
				musicInfo.setPreMusicName("bgm_battle");
				music.setVolume(musicInfo.getMusicVolume());
				musicInfo.setMusic(music);
				break;
			case MOVING_MUSIC : {
				String arrowName = fieldManager.getArrowName();
				musicInfo.setPreMusicName(arrowName);
				music = musicAssets.getMovingMusic(arrowName);
				music.setVolume(musicInfo.getMusicVolume());
				musicInfo.setMusic(music);
				break;
			}
			default :
				Gdx.app.log("MusicManager", "musicType정보 오류 - " + musicType);
				break;
		}
	}

	private boolean checkCurrentMusicIsNotNull() {
		return musicInfo.getMusic() != null;
	}

	private boolean checkCurrentMusicIsPlaying() {
		return musicInfo.getMusic().isPlaying();
	}

	private boolean checkIsSameWithCurrentMusic(String musicName, MusicEnum musicType) {
		switch (musicType) {
			case NORMAL :
				return musicInfo.getPreMusicName().equals(musicName);
			case BATTLE_MUSIC :
				return musicInfo.getPreMusicName().equals("bgm_battle");
			case MOVING_MUSIC :
				String arrowName = fieldManager.getArrowName();
				return musicInfo.getPreMusicName().equals(arrowName);
			case WORLD_NODE_MUSIC :
				String currentNode = positionManager.getCurrentNodePath();
				return musicInfo.getPreMusicName().equals(currentNode);
			default :
				Gdx.app.log("MusicManager", "musicType 정보 오류 - " + musicType);
				return false;
		}

	}

	public void setMusicAndPlay(String musicName) {
		setMusicAndPlay(musicName, MusicEnum.NORMAL, MusicCondition.WHENEVER);
	}

	public void setMusicAndPlay(String musicName, MusicCondition musicCondition) {
		setMusicAndPlay(musicName, MusicEnum.NORMAL, musicCondition);
	}

	public void setMusicAndPlay(MusicEnum musicType) {
		setMusicAndPlay("", musicType, MusicCondition.WHENEVER);
	}

	public void setMusic(String musicName) {
		musicInfo.setPreMusicName(musicName);
		Music music = musicAssets.getMusic(musicName);
		music.setVolume(musicInfo.getMusicVolume());
		musicInfo.setMusic(music);
	}

}
