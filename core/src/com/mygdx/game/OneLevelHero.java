package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.resource.Assets;
import com.mygdx.screen.VillageScreen;

public class OneLevelHero extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {

		Assets.load();
		setScreen(new VillageScreen(this, "Blackwood"));
	}

}
