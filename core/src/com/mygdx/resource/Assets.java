package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
	public static Skin skin;
	public static TextureRegionDrawable ibuttond;
	public static TextureRegionDrawable ibuttonu;
	public static TextureRegionDrawable ibuttonc;
	public static float soundVolume = 0.5f;
	public static float musicVolume = 0.5f;
	public static void load () {
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

	}
	public static void buttonload () {
		
		TextureAtlas textureAtlas = new TextureAtlas("data/imagebutton.atlas");

		ibuttond = new TextureRegionDrawable(textureAtlas.findRegion("buttondown"));
		ibuttonu = new TextureRegionDrawable(textureAtlas.findRegion("buttonup"));
				
	}

}
