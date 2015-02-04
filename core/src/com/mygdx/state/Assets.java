package com.mygdx.state;

//package com.mygdx.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.mygdx.model.Village;
import com.mygdx.model.WorldNode;

/**
 * 각종 리소스들을 관리해주는 assets 클래스, Stage및 Screen에 필요한 요소들을 전달해준다.
 * 
 * @author Velmont
 * 
 */

@Component
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

	public Map<String, JsonStringFile> filePathMap = new HashMap<>();
	public Map<String, String> jsonStringMap = new HashMap<>();
	public Map<String, TextureRegionDrawable> atlasUiMap = new HashMap<>();

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

	public void loadFilePath() {
		Gdx.app.debug("Assets", "loadFilePath()");
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("data/load/file_path.json").readString());
	}

	public void loadResourceFile() {
		Gdx.app.debug("Assets", "loadResourceFile()");
		atlasUiTextureLoad();
		fontLoad();
		chatButtonLoad();
		etcResourceLoad();
	}

	public void loadMapInfo() {
		Gdx.app.debug("Assets", "loadMapInfo()");
		roadInfoLoad();
		villageInfoLoad();
		worldMapInfoLoad();
	}

	public void loadUnitInfo() {
		Gdx.app.debug("Assets", "loadUnitInfo()");
		npcInfoLoad();
		heroInfoLoad();
		monsterInfoLoad();
	}

	// Stage 크기 설정
	public void loadSize(Stage stage) {
		Gdx.app.debug("Assets", "loadWindowSize()");
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	// JsonFile의 path를 읽어온다.
	public void loadJsonObject() {
		Gdx.app.debug("Assets", "loadJsonObject()");
		Map<String, JsonStringFile> jsonFileMap = JsonParser.parseMap(
				JsonStringFile.class,
				filePathMap.get(JsonEnum.JSON_FILE_PATH.toString()).getFile());
		for (Entry<String, JsonStringFile> entry : jsonFileMap.entrySet()) {
			jsonStringMap.put(entry.getKey(), entry.getValue().getFile());
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
				filePathMap.get(JsonEnum.ATLAS_UI_PATH.toString()).getFile());
		for (AtlasUiFile atlasUiFile : atlasUiFileList) {
			for (String element : atlasUiFile.getElement()) {
				atlasUiMap.put(element, new TextureRegionDrawable(atlasUiFile
						.getFile().findRegion(element)));
			}
		}
	}

	public void fontLoad() {
		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));
	}

	public void chatButtonLoad() {
		TextureAtlas textureAtlas = new TextureAtlas("skin/chatbutton.pack");
		chatButton = new TextureRegionDrawable[6];
		for (int i = 0; i < 6; i++) {
			chatButton[i] = new TextureRegionDrawable(
					textureAtlas.findRegion("chatbutton" + (i + 1)));
		}
	}

	// 임시용, 추후 제거 예정
	public void etcResourceLoad() {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		items = new TextureAtlas("texture/items/items.pack");
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/buyeo.mp3"));
	}
}
