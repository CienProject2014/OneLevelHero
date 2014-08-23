package com.mygdx.resource;

import java.util.HashMap;

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
import com.mygdx.stage.WorldStage.worldNode;

public class Assets {
	public static Skin skin;
	public static TextureAtlas items;
	public static TextureRegionDrawable ibuttond, ibuttonu, menu_button_down, menu_button_up, menu_button_toggle, credit_before, credit_after, extra_before, extra_after,
			option_before, option_after, start_after, start_before, downArrowButton, bagButton, nameAndTime, helpButton, optionButton, upArrowButton;

	//NPC얼굴
	public static Texture yongsa_happy, yongsa_sad, parath_angry, parath_happy, nothing_image;

	//JSON
	public static JSONObject worldmap_json, village_json, status_new_left, bag_json, credit_list, blackwood_json, prologue_json, blackwood_character, blackwood_background;
	public static Music music, mainMusic;
	public static Texture splash;
	public static Image logo;
	public static BitmapFont font;

	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;

	public static float realWidth;
	public static float realHeight;

	public static HashMap<String, String> villageHashmap;
	public static HashMap<String, worldNode> worldHashmap = new HashMap<String, worldNode>();

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		realWidth = vp.getViewportWidth();
		realHeight = vp.getViewportHeight();
	}

	public static void jsonLoad() {

		village_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/village.json").readString());
		worldmap_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/worldmap.json").readString());
		status_new_left = (JSONObject) JSONValue.parse(Gdx.files.internal("data/status/status_new_left.json").readString());
		bag_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/bag_new.json").readString());
		credit_list = (JSONObject) JSONValue.parse(Gdx.files.internal("data/credit_list.json").readString());
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		items = new TextureAtlas("texture/items/items.pack");
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/buyeo.mp3"));

		//prologue event json 로드
		prologue_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/event/prologue.json").readString());

		//blackwood event json 로드
		blackwood_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/event/blackwood.json").readString());
	}

	public static void menuScreenLoad() {

		TextureAtlas textureAtlas = new TextureAtlas("skin/MenuButton.pack");
		TextureAtlas buttonAtlas = new TextureAtlas("skin/Test1.atlas");

		logo = new Image(new TextureRegionDrawable(buttonAtlas.findRegion("title")));
		credit_before = new TextureRegionDrawable(textureAtlas.findRegion("button_credit_before"));
		credit_after = new TextureRegionDrawable(textureAtlas.findRegion("button_credit_after"));
		extra_before = new TextureRegionDrawable(textureAtlas.findRegion("button_extra_before"));
		extra_after = new TextureRegionDrawable(textureAtlas.findRegion("button_extra_after"));
		option_before = new TextureRegionDrawable(textureAtlas.findRegion("button_option_before"));
		option_after = new TextureRegionDrawable(textureAtlas.findRegion("button_option_after"));
		start_after = new TextureRegionDrawable(textureAtlas.findRegion("button_start_after"));
		start_before = new TextureRegionDrawable(textureAtlas.findRegion("button_start_after"));

	}

	public static void gameUILoad() {

		TextureAtlas textureAtlas = new TextureAtlas("data/UiButton.pack");
		downArrowButton = new TextureRegionDrawable(textureAtlas.findRegion("downArrowButton"));
		bagButton = new TextureRegionDrawable(textureAtlas.findRegion("bagButton"));
		nameAndTime = new TextureRegionDrawable(textureAtlas.findRegion("nameAndTime"));
		helpButton = new TextureRegionDrawable(textureAtlas.findRegion("helpButton"));
		optionButton = new TextureRegionDrawable(textureAtlas.findRegion("optionButton"));
		upArrowButton = new TextureRegionDrawable(textureAtlas.findRegion("upArrowButton"));

	}

	public static void characterImageLoad() {
		nothing_image = new Texture(Gdx.files.internal("texture/nothing_image.png"));
		yongsa_happy = new Texture(Gdx.files.internal("texture/npc/yongsa_happy.jpg"));
		yongsa_sad = new Texture(Gdx.files.internal("texture/npc/yongsa_sad.jpg"));
		parath_happy = new Texture(Gdx.files.internal("texture/npc/parath_happy.jpg"));
		parath_angry = new Texture(Gdx.files.internal("texture/npc/parath_angry.jpg"));
	}

	public static void fontLoad() {

		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));

	}

}
