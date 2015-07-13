package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Json;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.MusicFile;

public class MusicAssets implements FileAssetsInitializable {
	private Map<String, Music> musicMap = new HashMap<>();
	private Map<String, Music> worldNodeMusicMap = new HashMap<>();
	private Map<String, Music> battleMusicMap = new HashMap<>();
	private Map<String, Music> movingMusicMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public void set(Map<String, JsonStringFile> filePathMap) {
		Map<String, MusicFile> musicFileMap = JsonParser
				.parseMap(MusicFile.class,
						filePathMap.get(JsonEnum.MUSIC_FILE_PATH.toString())
								.loadFile());
		for (Entry<String, MusicFile> entry : musicFileMap.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			musicMap.put(entry.getKey(), entry.getValue().loadFile());
		}

		// WorldNode MusicList
		String worldNodeMusicJsonString = filePathMap.get(
				JsonEnum.WORLD_NODE_MUSIC_LIST.toString()).loadFile();
		Map<String, String> worldNodeMusicStringMap = new Json().fromJson(
				HashMap.class, worldNodeMusicJsonString);
		for (Entry<String, String> entry : worldNodeMusicStringMap.entrySet()) {
			worldNodeMusicMap.put(entry.getKey(),
					musicMap.get(entry.getValue()));
		}

		// Battle MusicList
		String battleMusicJsonString = filePathMap.get(
				JsonEnum.BATTLE_MUSIC_LIST.toString()).loadFile();
		Map<String, String> battleMusicStringMap = new Json().fromJson(
				HashMap.class, battleMusicJsonString);
		for (Entry<String, String> entry : battleMusicStringMap.entrySet()) {
			battleMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}

		// Moving MusicList
		String movingMusicJsonString = filePathMap.get(
				JsonEnum.MOVING_MUSIC_LIST.toString()).loadFile();
		Map<String, String> movingMusicStringMap = new Json().fromJson(
				HashMap.class, movingMusicJsonString);
		for (Entry<String, String> entry : movingMusicStringMap.entrySet()) {
			movingMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
		}
	}

	public Music getMusic(String musicString) {
		return musicMap.get(musicString);
	}

	public Music getWorldNodeMusic(String musicString) {
		return worldNodeMusicMap.get(musicString);
	}

	public Music getBattleMusicMap(String musicString) {
		return battleMusicMap.get(musicString);
	}

	public Music getMovingMusicMap(String musicString) {
		return movingMusicMap.get(musicString);
	}
}
