package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.resource.Assets;
import com.mygdx.screen.MenuScreen;

public class OneLevelHero extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");*/
		Assets.load();
		setScreen(new MenuScreen(this));
	}

/*	@Override
	public void render () {
		System.out.println("asdf");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
}
