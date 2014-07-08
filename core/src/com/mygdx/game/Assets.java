package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	public static Skin skin;
	
	public static void load () {
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

	}

}
