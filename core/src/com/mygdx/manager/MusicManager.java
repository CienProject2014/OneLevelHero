package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.assets.MusicAssets;
import com.mygdx.currentState.MusicInfo;
import com.mygdx.model.event.EventPacket;

public class MusicManager {
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private MusicAssets musicAssets;
	@Autowired
	private EventManager eventManager;
	private MusicInfo musicInfo = new MusicInfo();

	public enum MusicCondition {
		WHENEVER, IF_IS_NOT_PLAYING;
	}

	public void setMusicVolume(float musicVolume) {
		musicInfo.setMusicVolume(musicVolume);
	}

	public float getMusicVolume() {
		return musicInfo.getMusicVolume();
	}

	public void playMusic(Music music) {
		musicInfo.getMusic().play();
	}

	public void setMusic(Music music) {
		music.setVolume(getMusicVolume());
		musicInfo.setMusic(music);
	}

	public Music getMusic() {
		return musicInfo.getMusic();
	}

	public void setVolume(float volume) {
		musicInfo.getMusic().setVolume(volume);
	}

	public void playMusic() {
		musicInfo.getMusic().setLooping(true);
		musicInfo.getMusic().play();
	}

	public void stopMusic() {
		musicInfo.getMusic().stop();
	}

	public void setMusicAndPlay(Music music, float volume, MusicCondition musicCondition) {
		switch (musicCondition) {
			case WHENEVER :
				int delayTime = 2000;
				if (checkCurrentMusicIsNotNull()) {
					if (checkIsSameWithCurrentMusic(music)) {
						// Nothing happened
					} else {
						stopMusic();
						Timer.schedule(new Task() {
							@Override
							public void run() {
							}
						}, delayTime);
						setMusic(music);
						playMusic();
					}
				} else {
					setMusic(music);
					playMusic();
				}
				break;
			case IF_IS_NOT_PLAYING :
				if (checkCurrentMusicIsNotNull()) {
					if (musicInfo.getMusic().isPlaying())
						return;
				} else {
					setMusic(music);
					playMusic();
				}
				break;
			default :
				Gdx.app.error("MusicManager", "incorrect musicCondition");
		}
	}

	private boolean checkCurrentMusicIsNotNull() {
		return musicInfo.getMusic() != null;
	}

	private boolean checkCurrentMusicIsPlaying() {
		return musicInfo.getMusic().isPlaying();
	}

	private boolean checkIsSameWithCurrentMusic(Music music) {
		return musicInfo.getMusic().equals(music);
	}

	public void setMusicAndPlay(String musicName) {
		setMusicAndPlay(musicAssets.getMusic(musicName));
	}

	public void setMusicAndPlay(Music music, MusicCondition musicCondition) {
		setMusicAndPlay(music, musicInfo.getMusicVolume(), musicCondition);
	}

	public void setMusicAndPlay(Music music, float volume) {
		setMusicAndPlay(music, volume, MusicCondition.WHENEVER);
	}

	public void setMusicAndPlay(Music music) {
		setMusicAndPlay(music, musicInfo.getMusicVolume());
	}

	public void setWorldNodeMusicAndPlay() {
		String currentNode = positionManager.getCurrentNodeName();
		Music music = musicAssets.getWorldNodeMusic(currentNode);
		setMusicAndPlay(music);
	}

	public void setBattleMusicAndPlay() {
		String arrowName = fieldManager.getArrowName();
		Music music = musicAssets.getBattleMusic(arrowName);
		setMusicAndPlay(music);
	}

	public void setMovingMusicAndPlay() {
		String arrowName = fieldManager.getArrowName();
		Music music = musicAssets.getMovingMusic(arrowName);
		setMusicAndPlay(music);
	}

	public void setEventMusicAndPlay() {
		EventPacket eventPacket = eventManager.getCurrentNpcEventPacket();
		String code = eventPacket.getEventNpc() + "_" + eventPacket.getEventNumber();
		Music music = musicAssets.getEventMusic(code);
		setMusicAndPlay(music);
	}
}
