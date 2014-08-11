package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.loader.LoadLauncher;
import com.mygdx.resource.Assets;
import com.mygdx.screen.TestScreen;

public class OneLevelHero extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		Assets.jsonload();
		LoadLauncher.jsonSetting();
		setScreen(new TestScreen(this));
	}
}
