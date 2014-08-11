package com.mygdx.resource;

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
	public static TextureRegionDrawable ibuttond;
	public static TextureRegionDrawable ibuttonu;
	public static TextureRegionDrawable menu_button_down;
	public static TextureRegionDrawable menu_button_up;
	public static TextureRegionDrawable menu_button_toggle;

	public static TextureRegionDrawable credit_before;
	public static TextureRegionDrawable credit_after;
	public static TextureRegionDrawable extra_before;
	public static TextureRegionDrawable extra_after;
	public static TextureRegionDrawable option_before;
	public static TextureRegionDrawable option_after;
	public static TextureRegionDrawable start_after;
	public static TextureRegionDrawable start_before;
	public static TextureRegionDrawable downArrowButton;
	public static TextureRegionDrawable bagButton;
	public static TextureRegionDrawable nameAndTime;
	public static TextureRegionDrawable helpButton;
	public static TextureRegionDrawable optionButton;
	public static TextureRegionDrawable upArrowButton;
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

	public static void load() {

		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

	}

	public static void menuScreenButtonLoad() {

		TextureAtlas textureAtlas = new TextureAtlas("skin/MenuButton.pack");
		TextureAtlas buttonAtlas = new TextureAtlas("skin/Test1.atlas");

		logo = new Image(new TextureRegionDrawable(
				buttonAtlas.findRegion("title")));

		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));

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

	public static void gameUiButtonLoad() {

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
}
