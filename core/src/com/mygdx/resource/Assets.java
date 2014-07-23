package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
	public static Skin skin;
	public static TextureRegionDrawable ibuttond;
	public static TextureRegionDrawable ibuttonu;

	public static TextureRegionDrawable menu_button_down;
	public static TextureRegionDrawable menu_button_up;
	public static TextureRegionDrawable menu_button_toggle;
	public static Image logo;
	public static BitmapFont font;

	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;

	public static void load() {

		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

	}

	public static void buttonload() {

		TextureAtlas textureAtlas = new TextureAtlas("data/imagebutton.atlas");
		TextureAtlas buttonAtlas = new TextureAtlas("data/Test1.atlas");

		ibuttond = new TextureRegionDrawable(textureAtlas.findRegion("buttondown"));
		ibuttonu = new TextureRegionDrawable(textureAtlas.findRegion("buttonup"));

		menu_button_down = new TextureRegionDrawable(buttonAtlas.findRegion("button_down"));
		menu_button_up = new TextureRegionDrawable(buttonAtlas.findRegion("button_normal"));
		menu_button_toggle = new TextureRegionDrawable(buttonAtlas.findRegion("button_toggle"));
		logo = new Image(new TextureRegionDrawable(buttonAtlas.findRegion("title")));

		font = new BitmapFont(Gdx.files.internal("data/hangeul2.fnt"));
	}

}
