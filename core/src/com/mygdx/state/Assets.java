package com.mygdx.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.currentState.RoadInfo;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.AtlasUiFile;
import com.mygdx.model.Hero;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.Monster;
import com.mygdx.model.MusicFile;
import com.mygdx.model.NPC;
import com.mygdx.model.Village;
import com.mygdx.model.WorldNode;

/**
 * 각종 리소스들을 관리해주는 assets 클래스, Stage및 Screen에 필요한 요소들을 전달해준다.
 *
 * @author Velmont
 *
 */
public class Assets {
	public Skin skin;
	public TextureAtlas items;

	public TextureRegionDrawable[] chatButton;
	public Music music, mainMusic;
	public Texture splash;
	public Image logo;
	public BitmapFont font;

	public float soundVolume = 0.5f;
	public float musicVolume = 0.5f;

	public float windowWidth;
	public float windowHeight;

	public Map<String, RoadInfo> dungeonMap;

	public Map<String, JsonStringFile> filePathMap = new HashMap<String, JsonStringFile>();
	public Map<String, String> jsonStringMap = new HashMap<String, String>();
	public Map<String, TextureRegionDrawable> atlasUiMap = new HashMap<String, TextureRegionDrawable>();
	public Map<String, Music> musicMap = new HashMap<String, Music>();
	public Map<String, Music> worldNodeMusicMap = new HashMap<String, Music>();;
	public Map<String, Music> battleMusicMap = new HashMap<String, Music>();
	public Map<String, Music> movingMusicMap = new HashMap<String, Music>();

	public Map<String, Hero> heroMap;
	public Map<String, NPC> npcMap;
	public Map<String, Monster> monsterMap;
	public Map<String, Village> villageMap;
	public Map<String, WorldNode> worldNodeInfoMap;

	public Assets() {
		Gdx.app.debug("OneLevelHeroApplicationContext", "Assets Bean 우선 로드");
		loadAll();
	}

	public void loadAll() {
		// 해상도 설정
		// 화면의 Size를 별도로 설정해주어야 한다

		loadSize(new Stage());
		loadFilePath();
		loadJsonObject();
		loadResourceFile();
		loadMapInfo();
		loadUnitInfo();
	}

	private void loadFilePath() {
		Gdx.app.debug("Assets", "loadFilePath()");
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("data/load/file_path.json").readString());
	}

	private void loadResourceFile() {
		Gdx.app.debug("Assets", "loadResourceFile()");
		loadAtlasUiTexture();
		loadFont();
		loadChatButton();
		loadMusic();
		loadEtcResources();
	}

	private void loadMapInfo() {
		Gdx.app.debug("Assets", "loadMapInfo()");
		loadRoadInfo();
		loadVillageInfo();
		loadWorldMapInfo();
	}

	private void loadUnitInfo() {
		Gdx.app.debug("Assets", "loadUnitInfo()");
		loadNpcInfo();
		loadHeroInfo();
		loadMonsterInfo();
	}

	// Stage 크기 설정
	private void loadSize(Stage stage) {
		Gdx.app.debug("Assets", "loadWindowSize()");
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	// JsonFile의 path를 읽어온다.
	private void loadJsonObject() {
		Gdx.app.debug("Assets", "loadJsonObject()");
		Map<String, JsonStringFile> jsonFileMap = JsonParser.parseMap(
				JsonStringFile.class,
				filePathMap.get(JsonEnum.JSON_FILE_PATH.toString()).getFile());
		for (Entry<String, JsonStringFile> entry : jsonFileMap.entrySet())
			jsonStringMap.put(entry.getKey(), entry.getValue().getFile());
	}

	private void loadRoadInfo() {
	}

	private void loadHeroInfo() {
		// hero 리스트를 담은 Json을 불러와 객체화한다.
		heroMap = JsonParser.parseMap(Hero.class,
				jsonStringMap.get(JsonEnum.HERO_JSON.toString()));
	}

	private void loadWorldMapInfo() {
		worldNodeInfoMap = JsonParser.parseMap(WorldNode.class,
				jsonStringMap.get(JsonEnum.WORLDMAP_JSON.toString()));
	}

	private void loadMonsterInfo() {
		// monster 리스트를 담은 Json을 불러온다.
		monsterMap = JsonParser.parseMap(Monster.class,
				jsonStringMap.get(JsonEnum.MONSTER_JSON.toString()));
	}

	private void loadVillageInfo() {
		villageMap = JsonParser.parseMap(Village.class,
				jsonStringMap.get(JsonEnum.VILLAGE_JSON.toString()));
	}

	private void loadNpcInfo() {
		// npc 리스트를 담은 Json을 불러온다.
		npcMap = JsonParser.parseMap(NPC.class,
				jsonStringMap.get(JsonEnum.NPC_JSON.toString()));
	}

	private void loadAtlasUiTexture() {
		List<AtlasUiFile> atlasUiFileList = JsonParser.parseList(
				AtlasUiFile.class,
				filePathMap.get(JsonEnum.ATLAS_UI_PATH.toString()).getFile());
		for (AtlasUiFile atlasUiFile : atlasUiFileList)
			for (String element : atlasUiFile.getElement())
				atlasUiMap.put(element, new TextureRegionDrawable(atlasUiFile
						.getFile().findRegion(element)));
	}

	private void loadFont() {
		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));
	}

	private void loadChatButton() {
		TextureAtlas textureAtlas = new TextureAtlas("skin/chatbutton.pack");
		chatButton = new TextureRegionDrawable[6];
		for (int i = 0; i < 6; i++)
			chatButton[i] = new TextureRegionDrawable(
					textureAtlas.findRegion("chatbutton" + (i + 1)));
	}

	@SuppressWarnings("unchecked")
	private void loadMusic() {
		Map<String, MusicFile> musicFileMap = JsonParser.parseMap(
				MusicFile.class,
				filePathMap.get(JsonEnum.MUSIC_FILE_PATH.toString()).getFile());
		for (Entry<String, MusicFile> entry : musicFileMap.entrySet())
			musicMap.put(entry.getKey(), entry.getValue().getFile());

		// WorldNode MusicList
		String worldNodeMusicJsonString = filePathMap.get(
				JsonEnum.WORLD_NODE_MUSIC_LIST.toString()).getFile();
		Map<String, String> worldNodeMusicStringMap = new Json().fromJson(
				HashMap.class, worldNodeMusicJsonString);
		for (Entry<String, String> entry : worldNodeMusicStringMap.entrySet())
			worldNodeMusicMap.put(entry.getKey(),
					musicMap.get(entry.getValue()));

		// Battle MusicList
		String battleMusicJsonString = filePathMap.get(
				JsonEnum.BATTLE_MUSIC_LIST.toString()).getFile();
		Map<String, String> battleMusicStringMap = new Json().fromJson(
				HashMap.class, battleMusicJsonString);
		for (Entry<String, String> entry : battleMusicStringMap.entrySet())
			battleMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));

		// Moving MusicList
		String movingMusicJsonString = filePathMap.get(
				JsonEnum.MOVING_MUSIC_LIST.toString()).getFile();
		Map<String, String> movingMusicStringMap = new Json().fromJson(
				HashMap.class, movingMusicJsonString);
		for (Entry<String, String> entry : movingMusicStringMap.entrySet())
			movingMusicMap.put(entry.getKey(), musicMap.get(entry.getValue()));
	}

	// 임시용, 추후 제거 예정
	public void loadEtcResources() {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		items = new TextureAtlas("texture/items/items.pack");
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
	}
}
