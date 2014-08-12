package com.mygdx.resource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Assets {
	public static Skin skin;
	public static TextureRegionDrawable ibuttond, ibuttonu, menu_button_down, menu_button_up, menu_button_toggle, credit_before, credit_after, extra_before, extra_after,
			option_before, option_after, start_after, start_before, downArrowButton, bagButton, nameAndTime, helpButton, optionButton, upArrowButton;
	public static JSONObject prologue_json, script_json, charater_json, worldmap_json, village_json, status_json;

	public static Image logo;
	public static BitmapFont font;

	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;

	public static float realWidth;
	public static float realHeight;

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		realWidth = vp.getViewportWidth();
		realHeight = vp.getViewportHeight();
	}

	public static void jsonLoad() {
		prologue_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/scene_background.json").readString());
		script_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/scene_script.json").readString());
		charater_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/scene_character.json").readString());
		village_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/village.json").readString());
		worldmap_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/worldmap.json").readString());
		status_json = (JSONObject) JSONValue.parse(Gdx.files.internal("data/status_new.json").readString());
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));


	}

	public static void menuScreenButtonLoad() {

		TextureAtlas textureAtlas = new TextureAtlas("skin/MenuButton.pack");
		TextureAtlas buttonAtlas = new TextureAtlas("skin/Test1.atlas");

		logo = new Image(new TextureRegionDrawable(buttonAtlas.findRegion("title")));
		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));
		credit_before = new TextureRegionDrawable(textureAtlas.findRegion("button_credit_before"));
		credit_after = new TextureRegionDrawable(textureAtlas.findRegion("button_credit_after"));
		extra_before = new TextureRegionDrawable(textureAtlas.findRegion("button_extra_before"));
		extra_after = new TextureRegionDrawable(textureAtlas.findRegion("button_extra_after"));
		option_before = new TextureRegionDrawable(textureAtlas.findRegion("button_option_before"));
		option_after = new TextureRegionDrawable(textureAtlas.findRegion("button_option_after"));
		start_after = new TextureRegionDrawable(textureAtlas.findRegion("button_start_after"));
		start_before = new TextureRegionDrawable(textureAtlas.findRegion("button_start_after"));

	}

	public static void gameUiButtonLoad() {

		TextureAtlas textureAtlas = new TextureAtlas("data/UiButton.pack");
		downArrowButton = new TextureRegionDrawable(textureAtlas.findRegion("downArrowButton"));
		bagButton = new TextureRegionDrawable(textureAtlas.findRegion("bagButton"));
		nameAndTime = new TextureRegionDrawable(textureAtlas.findRegion("nameAndTime"));
		helpButton = new TextureRegionDrawable(textureAtlas.findRegion("helpButton"));
		optionButton = new TextureRegionDrawable(textureAtlas.findRegion("optionButton"));
		upArrowButton = new TextureRegionDrawable(textureAtlas.findRegion("upArrowButton"));

	}
}
