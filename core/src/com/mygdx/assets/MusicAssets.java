package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Json;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.model.jsonModel.MusicFile;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class MusicAssets implements FileAssetsInitializable {
	@Autowired
	private AssetsManager assetsManager;
	private Map<String, String> musicMap = new HashMap<>();
	private Map<String, String> worldNodeMusicMap = new HashMap<>();
	private Map<String, String> battleMusicMap = new HashMap<>();
	private Map<String, String> movingMusicMap = new HashMap<>();
	private Map<String, String> eventMusicMap = new HashMap<>();
	private Map<String, String> soundEffectMap = new HashMap<>();
	private Map<String, String> soundEffectInUseMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public void set(Map<String, StringFile> filePathMap) {
		Map<String, MusicFile> soundEffectFileMap = JsonParser.parseMap(MusicFile.class,
				filePathMap.get(JsonEnum.SOUND_EFFECT_FILE_PATH.toString()).loadFile());
		for (Entry<String, MusicFile> entry : soundEffectFileMap.entrySet()) {
			soundEffectMap.put(entry.getKey(), entry.getValue().loadFile());
			// assetsManager.load(soundEffectMap.get(entry.getKey()),
			// Sound.class);
		}

		Map<String, MusicFile> musicFileMap = JsonParser.parseMap(MusicFile.class,
				filePathMap.get(JsonEnum.MUSIC_FILE_PATH.toString()).loadFile());
		for (Entry<String, MusicFile> entry : musicFileMap.entrySet()) {
			musicMap.put(entry.getKey(), entry.getValue().loadFile());
			/// assetsManager.load(musicMap.get(entry.getKey()), Music.class);
		}

		// WorldNode MusicList
		String worldNodeMusicJsonString = filePathMap.get(JsonEnum.WORLD_NODE_MUSIC_LIST.toString()).loadFile();
		Map<String, String> worldNodeMusicStringMap = new Json().fromJson(HashMap.class, worldNodeMusicJsonString);
		for (Entry<String, String> entry : worldNodeMusicStringMap.entrySet()) {
			worldNodeMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// Battle MusicList
		String battleMusicJsonString = filePathMap.get(JsonEnum.BATTLE_MUSIC_LIST.toString()).loadFile();
		Map<String, String> battleMusicStringMap = new Json().fromJson(HashMap.class, battleMusicJsonString);
		for (Entry<String, String> entry : battleMusicStringMap.entrySet()) {
			battleMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// Moving MusicList
		String movingMusicJsonString = filePathMap.get(JsonEnum.MOVING_MUSIC_LIST.toString()).loadFile();
		Map<String, String> movingMusicStringMap = new Json().fromJson(HashMap.class, movingMusicJsonString);
		for (Entry<String, String> entry : movingMusicStringMap.entrySet()) {
			movingMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// SoundEffect List
		String soundEffectJsonString = filePathMap.get(JsonEnum.SOUND_EFECT_LIST.toString()).loadFile();
		Map<String, String> effectMusicStringMap = new Json().fromJson(HashMap.class, soundEffectJsonString);
		for (Entry<String, String> entry : effectMusicStringMap.entrySet()) {
			soundEffectInUseMap.put(entry.getKey(), soundEffectMap.get(entry.getValue()));
		}

		// Event MusicList
		String eventMusicJsonString = filePathMap.get(JsonEnum.EVENT_MUSIC_LIST.toString()).loadFile();
		Map<String, String> eventMusicStringMap = new Json().fromJson(HashMap.class, eventMusicJsonString);
		for (Entry<String, String> entry : eventMusicStringMap.entrySet()) {
			eventMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}
	}

	public Sound getSound(String soundString) {
		assetsManager.load(soundEffectMap.get(soundString), Sound.class);
		assetsManager.finishLoading();
		return assetsManager.get(soundEffectMap.get(soundString), Sound.class);
	}

	public Music getMusic(String musicString) {
		assetsManager.load(musicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		return assetsManager.get(musicMap.get(musicString), Music.class);
	}

	public Music getWorldNodeMusic(String musicString) {
		assetsManager.load(worldNodeMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		return assetsManager.get(worldNodeMusicMap.get(musicString), Music.class);
	}

	public Sound getSoundEffectByType(String soundType) {
		assetsManager.load(soundEffectInUseMap.get(soundType), Sound.class);
		assetsManager.finishLoading();
		return assetsManager.get(soundEffectInUseMap.get(soundType), Sound.class);
	}

	public Music getBattleMusic(String musicString) {
		assetsManager.load(battleMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		return assetsManager.get(battleMusicMap.get(musicString), Music.class);
	}

	public Music getMovingMusic(String musicString) {
		assetsManager.load(movingMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		return assetsManager.get(movingMusicMap.get(musicString), Music.class);
	}

	public Music getEventMusic(String musicString) {
		assetsManager.load(eventMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		return assetsManager.get(eventMusicMap.get(musicString), Music.class);
	}
}
