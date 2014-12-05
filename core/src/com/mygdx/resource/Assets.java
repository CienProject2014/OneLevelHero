package com.mygdx.resource;

import java.util.HashMap;
import java.util.Map;

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
import com.mygdx.model.Hero;
import com.mygdx.model.NPC;
import com.mygdx.stage.WorldStage.worldNode;

public class Assets {
	public static Skin skin;
	public static TextureAtlas items;

	public static TextureRegionDrawable ibuttond, ibuttonu, menu_button_down,
			menu_button_up, menu_button_toggle, credit_before, credit_after,
			extra_before, extra_after, option_before, option_after,
			start_after, start_before, downArrowButton, bagButton, nameAndTime,
			helpButton, optionButton, upArrowButton;
	public static TextureRegionDrawable[] chatButton;
	// NPC얼굴
	public static Texture parath_background, prog_team1, plan_team1,
			graphic_team1, scene1, scene2, scene3, scene4, rabbit1, rabbit2,
			rabbit3, yongsa_happy, yongsa_sad, parath_angry, yongsa_angry,
			parath_happy, waiji_happy, yongsa_sick, nothing_image,
			waiji_background, blackwood_center, main_background;

	// JSON
	public static JSONObject worldmap_json, village_json, status_new_left,
			bag_json, credit_list, blackwood_json, prologue_json,
			blackwood_character, blackwood_background, waiji_json, parath_json,
			npc_json, hero_json;

	// NPC 이름
	public static Texture yongsa, parath, waiji;

	public static Music music, mainMusic;
	public static Texture splash;
	public static Image logo;
	public static BitmapFont font;

	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;

	public static float realWidth;
	public static float realHeight;

	public static JSONObject jsonFile;
	public static HashMap<String, Object> resourceFileList = new HashMap<String, Object>();
	public static HashMap<String, Texture> imageFileList = new HashMap<String, Texture>();
	public static HashMap<String, String> villageHashmap;
	public static HashMap<String, worldNode> worldHashmap = new HashMap<String, worldNode>();
	public static Map<String, Hero> heroMap;
	public static Map<String, NPC> npcMap;

	public static void loadAll() {
		jsonLoad();
		menuScreenLoad();
		gameUILoad();
		characterImageLoad();
		fontLoad();
		chatButtonLoad();
		npcLoad();
		heroLoad();
		//해상도 설정

		// 화면의 Size를 별도로 설정해주어야 한다
		loadSize(new Stage());
	}

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		realWidth = vp.getViewportWidth();
		realHeight = vp.getViewportHeight();
	}

	private static void jsonLoad() {
		hero_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/event/hero.json").readString());
		npc_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/event/npc.json").readString());
		worldmap_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/worldmap.json").readString());
		village_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/village.json").readString());
		credit_list = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/credit_list.json").readString());

		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		items = new TextureAtlas("texture/items/items.pack");
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/buyeo.mp3"));

		parath_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/event/parath.json").readString());
		waiji_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/event/waiji.json").readString());
		// prologue event json 로드
		prologue_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/event/prologue.json").readString());
		// blackwood event json 로드
		blackwood_json = (JSONObject) JSONValue.parse(Gdx.files.internal(
				"data/event/blackwood.json").readString());

		// HashMap에 넣어두어 언제든지 Object타입으로 쓸 수 있도록 한다.
		resourceFileList.put("blackwood_json", blackwood_json);
		resourceFileList.put("prologue_json", prologue_json);
		resourceFileList.put("parath_json", parath_json);
		resourceFileList.put("waiji_json", waiji_json);
	}

	private static void heroLoad() {
		//hero 리스트를 담은 Json을 불러와 객체화한다.
		heroMap = JsonMapParser.mapParse(Hero.class, hero_json.toString());
	}

	private static void npcLoad() {
		//npc 리스트를 담은 Json을 불러온다.

		npcMap = JsonMapParser.mapParse(NPC.class, npc_json.toString());

	}

	private static void menuScreenLoad() {

		TextureAtlas textureAtlas = new TextureAtlas("skin/MenuButton.pack");
		TextureAtlas buttonAtlas = new TextureAtlas("skin/Test1.atlas");

		logo = new Image(new TextureRegionDrawable(
				buttonAtlas.findRegion("title")));
		credit_before = new TextureRegionDrawable(
				textureAtlas.findRegion("button_credit_before"));
		credit_after = new TextureRegionDrawable(
				textureAtlas.findRegion("button_credit_after"));
		extra_before = new TextureRegionDrawable(
				textureAtlas.findRegion("button_extra_before"));
		extra_after = new TextureRegionDrawable(
				textureAtlas.findRegion("button_extra_after"));
		option_before = new TextureRegionDrawable(
				textureAtlas.findRegion("button_option_before"));
		option_after = new TextureRegionDrawable(
				textureAtlas.findRegion("button_option_after"));
		start_after = new TextureRegionDrawable(
				textureAtlas.findRegion("button_start_after"));
		start_before = new TextureRegionDrawable(
				textureAtlas.findRegion("button_start_after"));

	}

	private static void gameUILoad() {

		TextureAtlas textureAtlas = new TextureAtlas("data/UiButton.pack");
		downArrowButton = new TextureRegionDrawable(
				textureAtlas.findRegion("downArrowButton"));
		bagButton = new TextureRegionDrawable(
				textureAtlas.findRegion("bagButton"));
		nameAndTime = new TextureRegionDrawable(
				textureAtlas.findRegion("nameAndTime"));
		helpButton = new TextureRegionDrawable(
				textureAtlas.findRegion("helpButton"));
		optionButton = new TextureRegionDrawable(
				textureAtlas.findRegion("optionButton"));
		upArrowButton = new TextureRegionDrawable(
				textureAtlas.findRegion("upArrowButton"));

	}

	private static void characterImageLoad() {

		yongsa = new Texture(Gdx.files.internal("texture/npc/YongSa.jpg"));
		parath = new Texture(Gdx.files.internal("texture/npc/Parath.png"));
		waiji = new Texture(Gdx.files.internal("texture/npc/waiji_happy.png"));

		rabbit1 = new Texture(
				Gdx.files.internal("texture/prologue/rabbit1.png"));
		rabbit2 = new Texture(
				Gdx.files.internal("texture/prologue/rabbit2.png"));
		rabbit3 = new Texture(
				Gdx.files.internal("texture/prologue/rabbit3.png"));
		nothing_image = new Texture(
				Gdx.files.internal("texture/nothing_image.png"));
		yongsa_happy = new Texture(
				Gdx.files.internal("texture/npc/yongsa_happy.jpg"));
		yongsa_sick = new Texture(
				Gdx.files.internal("texture/npc/yongsa_sick.jpg"));
		yongsa_sad = new Texture(
				Gdx.files.internal("texture/npc/yongsa_sad.png"));
		parath_happy = new Texture(
				Gdx.files.internal("texture/npc/parath_happy.jpg"));
		yongsa_angry = new Texture(
				Gdx.files.internal("texture/npc/yongsa_angry.png"));
		parath_angry = new Texture(
				Gdx.files.internal("texture/npc/parath_angry.jpg"));
		waiji_happy = new Texture(
				Gdx.files.internal("texture/npc/waiji_happy.png"));

		scene1 = new Texture(Gdx.files.internal("texture/prologue/scene1.jpg"));
		scene2 = new Texture(Gdx.files.internal("texture/prologue/scene2.jpg"));
		scene3 = new Texture(Gdx.files.internal("texture/prologue/scene3.jpg"));
		scene4 = new Texture(Gdx.files.internal("texture/prologue/scene4.jpg"));

		prog_team1 = new Texture(
				Gdx.files.internal("texture/credit/prog_team1.png"));
		plan_team1 = new Texture(
				Gdx.files.internal("texture/credit/plan_team1.png"));
		graphic_team1 = new Texture(
				Gdx.files.internal("texture/credit/graphic_team1.png"));
		parath_background = new Texture(
				Gdx.files.internal("texture/npc/parath_background.png"));
		waiji_background = new Texture(
				Gdx.files.internal("texture/npc/waiji_background.png"));
		blackwood_center = new Texture(
				Gdx.files.internal("texture/blackwood/blackwood_center.png"));
		main_background = new Texture(
				Gdx.files.internal("texture/MainMenu_Background.png"));

		// HashMap에 넣어두어 언제든지 Object타입으로 쓸 수 있도록 한다.

		imageFileList.put("yongsa_happy", yongsa_happy);
		imageFileList.put("yongsa_sad", yongsa_sad);
		imageFileList.put("parath_happy", parath_happy);
		imageFileList.put("parath_angry", parath_angry);
		imageFileList.put("waiji_happy", waiji_happy);
		imageFileList.put("rabbit1", rabbit1);
		imageFileList.put("rabbit2", rabbit2);
		imageFileList.put("rabbit3", rabbit3);
		imageFileList.put("scene1", scene1);
		imageFileList.put("scene2", scene2);
		imageFileList.put("scene3", scene3);
		imageFileList.put("scene4", scene4);
		imageFileList.put("prog_team1", prog_team1);
		imageFileList.put("plan_team1", plan_team1);
		imageFileList.put("graphic_team1", graphic_team1);
		imageFileList.put("nothing_image", nothing_image);
		imageFileList.put("parath_background", parath_background);
		imageFileList.put("waiji_background", waiji_background);
		imageFileList.put("blackwood_center", blackwood_center);
		imageFileList.put("yongsa_sick", yongsa_sick);
		imageFileList.put("yongsa_angry", yongsa_angry);
		imageFileList.put("main_background", main_background);
		imageFileList.put("yongsa", yongsa);
		imageFileList.put("parath", parath);
		imageFileList.put("waiji", waiji);
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
}
