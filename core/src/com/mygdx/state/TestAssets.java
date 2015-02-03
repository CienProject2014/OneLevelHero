package com.mygdx.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
import com.mygdx.currentState.RoadInfo;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.AtlasUiFile;
import com.mygdx.model.Hero;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.Monster;
import com.mygdx.model.NPC;
import com.mygdx.model.TextureFile;
import com.mygdx.model.Village;
import com.mygdx.model.WorldNode;

@Component
@Qualifier("test")
public class TestAssets implements Assets {
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

	public Map<String, JsonStringFile> filePathMap = new HashMap<>();
	public Map<String, String> jsonStringMap = new HashMap<>();
	public Map<String, Texture> characterTextureMap = new HashMap<>();
	public Map<String, Texture> monsterTextureMap = new HashMap<>();
	public Map<String, Texture> backgroundTextureMap = new HashMap<>();
	public Map<String, TextureRegionDrawable> atlasUiMap = new HashMap<>();

	public Map<String, Hero> heroMap;
	public Map<String, NPC> npcMap;
	public Map<String, Monster> monsterMap;
	public Map<String, Village> villageMap;
	public Map<String, WorldNode> worldNodeInfoMap;

	public void loadAll() {
		loadFilePath();
		jsonObjectLoad();
		resourceFileLoad();
		mapInfoLoad();
		unitInfoLoad();
	}

	public void resourceFileLoad() {
		atlasUiTextureLoad();
		sceneTextureLoad();
		fontLoad();
		chatButtonLoad();
		etcResourceLoad();
	}

	public void mapInfoLoad() {
		roadInfoLoad();
		villageInfoLoad();
		worldMapInfoLoad();
	}

	public void unitInfoLoad() {
		npcInfoLoad();
		heroInfoLoad();
		monsterInfoLoad();
	}

	public void loadFilePath() {
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("../android/assets/data/load/file_path.json")
				.readString());
	}

	// Stage 크기 설정
	public void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	// JsonFile의 path를 읽어온다.
	public void jsonObjectLoad() {
		Map<String, JsonStringFile> jsonFileMap = JsonParser.parseMap(
				JsonStringFile.class,
				filePathMap.get(JsonEnum.JSON_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, JsonStringFile> entry : jsonFileMap.entrySet()) {
			jsonStringMap.put(entry.getKey(), entry.getValue().getTestFile());
		}
	}

	public void roadInfoLoad() {

	}

	public void heroInfoLoad() {
		// hero 리스트를 담은 Json을 불러와 객체화한다.
		heroMap = JsonParser.parseMap(Hero.class,
				jsonStringMap.get(JsonEnum.HERO_JSON.toString()));
	}

	public void worldMapInfoLoad() {
		worldNodeInfoMap = JsonParser.parseMap(WorldNode.class,
				jsonStringMap.get(JsonEnum.WORLDMAP_JSON.toString()));
	}

	public void monsterInfoLoad() {
		// monster 리스트를 담은 Json을 불러온다.
		monsterMap = JsonParser.parseMap(Monster.class,
				jsonStringMap.get(JsonEnum.MONSTER_JSON.toString()));
	}

	public void villageInfoLoad() {
		villageMap = JsonParser.parseMap(Village.class,
				jsonStringMap.get(JsonEnum.VILLAGE_JSON.toString()));
	}

	public void npcInfoLoad() {
		// npc 리스트를 담은 Json을 불러온다.
		npcMap = JsonParser.parseMap(NPC.class,
				jsonStringMap.get(JsonEnum.NPC_JSON.toString()));
	}

	public void atlasUiTextureLoad() {
		List<AtlasUiFile> atlasUiFileList = JsonParser.parseList(
				AtlasUiFile.class,
				filePathMap.get(JsonEnum.ATLAS_UI_PATH.toString())
						.getTestFile());
		for (AtlasUiFile atlasUiFile : atlasUiFileList) {
			for (String element : atlasUiFile.getElement()) {
				atlasUiMap.put(element, new TextureRegionDrawable(atlasUiFile
						.getTestFile().findRegion(element)));
			}
		}
	}

	public void sceneTextureLoad() {
		// character
		Map<String, TextureFile> characterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.CHARACTER_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet()) {
			characterTextureMap.put(entry.getKey(), entry.getValue()
					.getTestFile());
		}

		Map<String, TextureFile> monsterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.MONSTER_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, TextureFile> entry : monsterFileMap.entrySet()) {
			monsterTextureMap.put(entry.getKey(), entry.getValue()
					.getTestFile());
		}

		// background
		Map<String, TextureFile> backgroundFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.BACKGROUND_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, TextureFile> entry : backgroundFileMap.entrySet()) {
			backgroundTextureMap.put(entry.getKey(), entry.getValue()
					.getTestFile());
		}
	}

	public void fontLoad() {
		font = new BitmapFont(
				Gdx.files.internal("../android/assets/skin/hangeul2.fnt"));
	}

	public void chatButtonLoad() {
		TextureAtlas textureAtlas = new TextureAtlas(
				"../android/assets/skin/chatbutton.pack");
		chatButton = new TextureRegionDrawable[6];
		for (int i = 0; i < 6; i++) {
			chatButton[i] = new TextureRegionDrawable(
					textureAtlas.findRegion("chatbutton" + (i + 1)));
		}
	}

	// 임시용, 추후 제거 예정
	public void etcResourceLoad() {
		skin = new Skin(
				Gdx.files.internal("../android/assets/skin/uiskin.json"));
		items = new TextureAtlas("../android/assets/texture/items/items.pack");
		splash = new Texture(
				Gdx.files.internal("../android/assets/texture/splash.png"));
		mainMusic = Gdx.audio.newMusic(Gdx.files
				.internal("../android/assets/data/buyeo.mp3"));
	}
}
