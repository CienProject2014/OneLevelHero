package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.model.jsonModel.StringFile;

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
	private String preMusic = "";
	private boolean check;

	@SuppressWarnings("unchecked")
	@Override
	public void set(Map<String, StringFile> filePathMap) {
		FileHandle musicfile = Gdx.files.local("musicMap.json");
		Json json = new Json();
		if (Gdx.app.getType() == ApplicationType.Android) {
			musicMap = json.fromJson(HashMap.class, Gdx.files.internal("music/musicMap.json"));
		} else {
			directoryMusicMapper(musicMap, "music");
			musicfile.writeString(json.prettyPrint(musicMap), false);
			musicMap = json.fromJson(HashMap.class, Gdx.files.internal("music/musicMap.json"));
		}

		FileHandle soundfile = Gdx.files.local("soundMap.json");
		if (Gdx.app.getType() == ApplicationType.Android) {
			soundEffectMap = json.fromJson(HashMap.class, Gdx.files.internal("sound/soundMap.json"));
		} else {
			directoryMusicMapper(soundEffectMap, "sound");
			soundfile.writeString(json.prettyPrint(soundEffectMap), false);
			soundEffectMap = json.fromJson(HashMap.class, Gdx.files.internal("sound/soundMap.json"));
		}

		// WorldNode MusicList
		String worldNodeMusicJsonString = filePathMap.get(String.valueOf(JsonEnum.WORLD_NODE_MUSIC_LIST)).loadFile();
		Map<String, String> worldNodeMusicStringMap = new Json().fromJson(HashMap.class, worldNodeMusicJsonString);
		for (Entry<String, String> entry : worldNodeMusicStringMap.entrySet()) {
			worldNodeMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// Battle MusicList
		String battleMusicJsonString = filePathMap.get(String.valueOf(JsonEnum.BATTLE_MUSIC_LIST)).loadFile();
		Map<String, String> battleMusicStringMap = new Json().fromJson(HashMap.class, battleMusicJsonString);
		for (Entry<String, String> entry : battleMusicStringMap.entrySet()) {
			battleMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// Moving MusicList
		String movingMusicJsonString = filePathMap.get(String.valueOf(JsonEnum.MOVING_MUSIC_LIST)).loadFile();
		Map<String, String> movingMusicStringMap = new Json().fromJson(HashMap.class, movingMusicJsonString);
		for (Entry<String, String> entry : movingMusicStringMap.entrySet()) {
			movingMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// SoundEffect List
		String soundEffectJsonString = filePathMap.get(String.valueOf(JsonEnum.SOUND_EFECT_LIST)).loadFile();
		Map<String, String> effectMusicStringMap = new Json().fromJson(HashMap.class, soundEffectJsonString);
		for (Entry<String, String> entry : effectMusicStringMap.entrySet()) {
			soundEffectInUseMap.put(entry.getKey(), soundEffectMap.get(entry.getValue()));
		}

		// Event MusicList
		String eventMusicJsonString = filePathMap.get(String.valueOf(JsonEnum.EVENT_MUSIC_LIST)).loadFile();
		Map<String, String> eventMusicStringMap = new Json().fromJson(HashMap.class, eventMusicJsonString);
		for (Entry<String, String> entry : eventMusicStringMap.entrySet()) {
			eventMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}
	}

	public void directoryMusicMapper(Map<String, String> map, String path) {
		FileHandle fh;

		if (Gdx.app.getType() == ApplicationType.Android) {
			fh = Gdx.files.internal(path);
		} else { // ApplicationType.Desktop ..
			fh = Gdx.files.internal("./bin/" + path);
		}

		directoryMusicMapperRecursive(map, fh);
	}

	public void directoryMusicMapperRecursive(Map<String, String> map, FileHandle fh) {
		if (fh.isDirectory()) {
			FileHandle[] fhs = fh.list();

			for (FileHandle e : fhs) {
				directoryMusicMapperRecursive(map, e);
			}
		} else if (!map.containsKey(fh.nameWithoutExtension()) && fh.extension().matches("^(mp3)")) {
			String[] path = fh.path().toString().split("/");
			String realPath = "";
			for (int i = 0; i < path.length - 2; i++) {
				realPath += path[i + 2];
				realPath += "/";
			}
			map.put(fh.nameWithoutExtension(), realPath);
		}
	}

	public Sound getSound(String soundString) {
		if (soundEffectMap.containsKey(soundString)) {
			assetsManager.load(soundEffectMap.get(soundString), Sound.class);
			assetsManager.finishLoading();
			return assetsManager.get(soundEffectMap.get(soundString), Sound.class);
		} else {
			assetsManager.load(soundEffectMap.get("slash"), Sound.class);
			assetsManager.finishLoading();
			return assetsManager.get(soundEffectMap.get("slash"), Sound.class);
		}

	}

	public Sound getSoundEffectByType(String soundType) {
		assetsManager.load(soundEffectInUseMap.get(soundType), Sound.class);
		assetsManager.finishLoading();
		return assetsManager.get(soundEffectInUseMap.get(soundType), Sound.class);
	}

	public Music getMusic(String musicString) {
		if (preMusic != "") {
			check = true;
		}
		if (check == true) {
			if (assetsManager.isLoaded(musicMap.get(preMusic))) {
				assetsManager.unload(musicMap.get(preMusic));
			}
		}
		assetsManager.load(musicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		preMusic = musicString;
		return assetsManager.get(musicMap.get(musicString), Music.class);
	}

	public Music getWorldNodeMusic(String musicString) {
		if (preMusic != "") {
			check = true;
		}
		if (check == true) {
			if (assetsManager.isLoaded(musicMap.get(preMusic))) {
				assetsManager.unload(musicMap.get(preMusic));
			}
		}
		assetsManager.load(worldNodeMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		preMusic = musicString;
		return assetsManager.get(worldNodeMusicMap.get(musicString), Music.class);
	}

	public Music getBattleMusic(String musicString) {
		if (preMusic != "") {
			check = true;
		}
		if (check == true) {
			if (assetsManager.isLoaded(musicMap.get(preMusic))) {
				assetsManager.unload(musicMap.get(preMusic));
			}
		}
		assetsManager.load(battleMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		preMusic = musicString;
		return assetsManager.get(battleMusicMap.get(musicString), Music.class);
	}

	public Music getMovingMusic(String musicString) {
		if (preMusic != "") {
			check = true;
		}
		if (check == true) {
			if (assetsManager.isLoaded(musicMap.get(preMusic))) {
				assetsManager.unload(musicMap.get(preMusic));
			}
		}
		assetsManager.load(movingMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		preMusic = musicString;
		return assetsManager.get(movingMusicMap.get(musicString), Music.class);
	}

	public Music getEventMusic(String musicString) {
		if (preMusic != "") {
			check = true;
		}
		if (check == true) {
			if (assetsManager.isLoaded(musicMap.get(preMusic))) {
				assetsManager.unload(musicMap.get(preMusic));
			}
		}
		assetsManager.load(eventMusicMap.get(musicString), Music.class);
		assetsManager.finishLoading();
		preMusic = musicString;
		return assetsManager.get(eventMusicMap.get(musicString), Music.class);
	}
}
