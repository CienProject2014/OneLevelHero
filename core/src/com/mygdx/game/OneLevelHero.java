package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.screen.LoadingBarScreen;

public class OneLevelHero extends Game {

	@Override
	public void create() {
		setScreen(new LoadingBarScreen(this));
	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			// Do back button handling (show pause menu?)
			// This will exit the app but you can add other
			// options here as wellc
		}
		return false;
	}
}
