package com.mygdx.state;

//package com.mygdx.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
import com.mygdx.manager.JsonMapParser;
import com.mygdx.model.AtlasUiFile;
import com.mygdx.model.DungeonInfo;
import com.mygdx.model.Hero;
import com.mygdx.model.JsonFile;
import com.mygdx.model.Monster;
import com.mygdx.model.NPC;
import com.mygdx.model.TextureFile;
import com.mygdx.stage.WorldStage.worldNode;

public class Assets {
	public static Skin skin;
	public static TextureAtlas items;

	public static TextureRegionDrawable[] chatButton;

	// JSON
	public static JSONObject json_file_path, character_file_path,
			background_file_path, atlas_ui_path;

	// JSON
	public static JSONObject worldmap_json, village_json, dungeon_json,
			status_new_left, bag_json, credit_list, blackwood_json,
			prologue_json, blackwood_character, blackwood_background,
			monster_json;

	public static Music music, mainMusic;
	public static Texture splash;
	public static Image logo;
	public static BitmapFont font;

	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;

	public static float windowWidth;
	public static float windowHeight;

	public static Map<String, DungeonInfo> dungeonMap;

	public static Map<String, JSONObject> jsonObjectMap = new HashMap<>();
	public static Map<String, JsonFile> jsonFileMap = new HashMap<>();

	private static Map<String, TextureFile> characterFileMap = new HashMap<>();
	public static Map<String, Texture> characterTextureMap = new HashMap<>();

	private static Map<String, TextureFile> backgroundFileMap = new HashMap<>();
	public static Map<String, Texture> backgroundTextureMap = new HashMap<>();

	public static Map<String, worldNode> worldHashmap = new HashMap<>();
	public static List<String> menuScreenUiFileList = new ArrayList<>();

	private static Map<String, AtlasUiFile> atlasUiFileMap;
	public static Map<String, TextureRegionDrawable> atlasUiMap = new HashMap<>();;

	public static Map<String, Hero> heroMap;
	public static Map<String, NPC> npcMap;
	public static Map<String, Monster> monsterMap;

	public static void loadAll() {
		jsonObjectLoad();
		atlasUiTextureLoad();
		sceneTextureLoad();
		fontLoad();
		chatButtonLoad();
		dungeonLoad();
		npcLoad();
		heroLoad();
		monsterLoad();
		etcResourceLoad();
		// 해상도 설정

		// 화면의 Size를 별도로 설정해주어야 한다
		loadSize(new Stage());
	}

	// Stage 크기 설정
	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	//JsonFile의 path를 읽어온다.
	private static void jsonObjectLoad() {
		json_file_path = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/load/json_file_path.json").readString());
		jsonFileMap = JsonMapParser.mapParse(JsonFile.class,
				json_file_path.toString());
		for (Entry<String, JsonFile> entry : jsonFileMap.entrySet()) {
			jsonObjectMap.put(entry.getKey(), entry.getValue().getFile());
		}
	}

	private static void dungeonLoad() {
		dungeonMap = JsonMapParser.mapParse(DungeonInfo.class, jsonObjectMap
				.get("dungeon_json").get("actual").toString());

	}

	private static void heroLoad() {
		// hero 리스트를 담은 Json을 불러와 객체화한다.
		heroMap = JsonMapParser.mapParse(Hero.class,
				jsonObjectMap.get("hero_json").toString());

	}

	private static void npcLoad() {
		// npc 리스트를 담은 Json을 불러온다.
		npcMap = JsonMapParser.mapParse(NPC.class, jsonObjectMap
				.get("npc_json").toString());

	}

	private static void monsterLoad() {
		// monster 리스트를 담은 Json을 불러온다.
		monsterMap = JsonMapParser.mapParse(Monster.class,
				jsonObjectMap.get("monster_json").toString());

	}

	private static void atlasUiTextureLoad() {
		atlas_ui_path = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/load/atlas_ui_path.json").readString());
		atlasUiFileMap = JsonMapParser.mapParse(AtlasUiFile.class,
				atlas_ui_path.toString());
		for (Entry<String, AtlasUiFile> entry : atlasUiFileMap.entrySet()) {
			//FIXME atlasUiFileMap의 자료구조는 Map인데 Key값은 전혀 사용되지 않음
			for (String element : entry.getValue().getElement())
				atlasUiMap.put(element, new TextureRegionDrawable(entry
						.getValue().getFile().findRegion(element)));
		}
	}

	private static void sceneTextureLoad() {
		//character
		character_file_path = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/load/character_file_path.json").readString());
		characterFileMap = JsonMapParser.mapParse(TextureFile.class,
				character_file_path.toString());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet()) {
			characterTextureMap.put(entry.getKey(), entry.getValue().getFile());
		}

		//background
		background_file_path = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/load/background_file_path.json").readString());
		backgroundFileMap = JsonMapParser.mapParse(TextureFile.class,
				background_file_path.toString());
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
