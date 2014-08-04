package com.mygdx.screen;

import com.badlogic.gdx.Screen;
import com.mygdx.game.OneLevelHero;

public class MovingScreen implements Screen {
	private OneLevelHero game;

	MovingScreen(OneLevelHero game) {
		this.setGame(game);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public OneLevelHero getGame() {
		return game;
	}

	public void setGame(OneLevelHero game) {
		this.game = game;
	}

}
