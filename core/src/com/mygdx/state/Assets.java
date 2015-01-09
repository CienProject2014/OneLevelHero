package com.mygdx.state;

//package com.mygdx.state;

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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.AtlasUiFile;
import com.mygdx.model.DungeonInfo;
import com.mygdx.model.Hero;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.Monster;
import com.mygdx.model.NPC;
import com.mygdx.model.TextureFile;
import com.mygdx.model.Village;
import com.mygdx.model.WorldMapInfo;

/**
 * 각종 리소스들을 static하게 관리해주는 Assets 클래스
 * Stage및 Screen에 필요한 요소들을 전달해준다.
 * @author Velmont
 * 
 */
public class Assets {
	public static Skin skin;
	public static TextureAtlas items;

	public static TextureRegionDrawable[] chatButton;
	public static Music music, mainMusic;
	public static Texture splash;
	public static Image logo;
	public static BitmapFont font;

	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;

	public static float windowWidth;
	public static float windowHeight;

	public static Map<String, DungeonInfo> dungeonMap;

	public static Map<String, JsonStringFile> filePathMap = new HashMap<>();
	public static Map<String, String> jsonMap = new HashMap<>();
	public static Map<String, Texture> characterTextureMap = new HashMap<>();
	public static Map<String, Texture> backgroundTextureMap = new HashMap<>();
	public static Map<String, TextureRegionDrawable> atlasUiMap = new HashMap<>();

	public static Map<String, Hero> heroMap;
	public static Map<String, NPC> npcMap;
	public static Map<String, Monster> monsterMap;
	public static Map<String, Village> villageMap;
	public static WorldMapInfo worldInfo;

	public static void loadAll() {
		loadFilePath();
		jsonObjectLoad();
		resourceFileLoad();
		mapInfoLoad();
		unitInfoLoad();

		// 해상도 설정
		// 화면의 Size를 별도로 설정해주어야 한다
		loadSize(new Stage());
	}

	private static void resourceFileLoad() {
		atlasUiTextureLoad();
		sceneTextureLoad();
		fontLoad();
		chatButtonLoad();
		etcResourceLoad();

	}

	private static void mapInfoLoad() {
		dungeonInfoLoad();
		villageInfoLoad();
		worldInfoLoad();
	}

	private static void unitInfoLoad() {
		npcInfoLoad();
		heroInfoLoad();
		monsterInfoLoad();
	}

	private static void loadFilePath() {
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("data/load/file_path.json").readString());
	}

	// Stage 크기 설정
	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	//JsonFile의 path를 읽어온다.
	private static void jsonObjectLoad() {
		Map<String, JsonStringFile> jsonFileMap = JsonParser.parseMap(
				JsonStringFile.class,
				filePathMap.get(JsonEnum.JSON_FILE_PATH.getJsonName())
						.getFile());
		for (Entry<String, JsonStringFile> entry : jsonFileMap.entrySet()) {
			jsonMap.put(entry.getKey(), entry.getValue().getFile());
		}
	}

	private static void dungeonInfoLoad() {
		//dungeonMap = JsonParser.parseMap(DungeonInfo.class,jsonMap.get(JsonEnum.DUNGEON_JSON.getJsonName()));

	}

	private static void heroInfoLoad() {

		//hero 리스트를 담은 Json을 불러와 객체화한다.
		heroMap = JsonParser.parseMap(Hero.class,
				jsonMap.get(JsonEnum.HERO_JSON.getJsonName()));

	}

	private static void worldInfoLoad() {
		//worldInfo = new Json().fromJson(WorldMapInfo.class,jsonMap.get(JsonEnum.WORLDMAP_JSON.getJsonName()));
	}

	private static void monsterInfoLoad() {
		// monster 리스트를 담은 Json을 불러온다.
		monsterMap = JsonParser.parseMap(Monster.class,
				jsonMap.get(JsonEnum.MONSTER_JSON.getJsonName()));
	}

	private static void villageInfoLoad() {
		//village 리스트를 담은 Json을 불러와 객체화한다.
		villageMap = JsonParser.parseMap(Village.class,
				jsonMap.get(JsonEnum.VILLAGE_JSON.getJsonName()));
	}

	private static void npcInfoLoad() {
		//npc 리스트를 담은 Json을 불러온다.
		npcMap = JsonParser.parseMap(NPC.class,
				jsonMap.get(JsonEnum.NPC_JSON.getJsonName()));
	}

	private static void atlasUiTextureLoad() {
		List<AtlasUiFile> atlasUiFileList = JsonParser
				.parseList(AtlasUiFile.class,

				filePathMap.get(JsonEnum.ATLAS_UI_PATH.getJsonName()).getFile());
		for (AtlasUiFile atlasUiFile : atlasUiFileList) {
			for (String element : atlasUiFile.getElement()) {
				atlasUiMap.put(element, new TextureRegionDrawable(atlasUiFile
						.getFile().findRegion(element)));
			}
		}
	}

	private static void sceneTextureLoad() {
		//character
		Map<String, TextureFile> characterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.CHARACTER_FILE_PATH.getJsonName())
						.getFile());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet()) {
			characterTextureMap.put(entry.getKey(), entry.getValue().getFile());
		}

		//background
		Map<String, TextureFile> backgroundFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.BACKGROUND_FILE_PATH.getJsonName())
						.getFile());
		for (Entry<String, TextureFile> entry : backgroundFileMap.entrySet()) {
			backgroundTextureMap
					.put(entry.getKey(), entry.getValue().getFile());
		}
	}

	private static void fontLoad() {

		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));

	}

	private static void chatButtonLoad() {
		TextureAtlas textureAtlas = new TextureAtlas("skin/chatbutton.pack");
		chatButton = new TextureRegionDrawable[6];
		for (int i = 0; i < 6; i++) {
			chatButton[i] = new TextureRegionDrawable(
					textureAtlas.findRegion("chatbutton" + (i + 1)));
		}
	}

	//임시용, 추후 제거 예정
	private static void etcResourceLoad() {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		items = new TextureAtlas("texture/items/items.pack");
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/buyeo.mp3"));
	}
}
