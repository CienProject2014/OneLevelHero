package com.mygdx.game;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.ScreenManager;

public class OneLevelHero extends Game {
	@Autowired
	private LoadManager loadManager;

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		new ScreenController(ScreenEnum.MENU);
		Gdx.input.setCatchBackKey(true);
	}

	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.BACK) {
			// Do back button handling (show pause menu?)
			// This will exit the app but you can add other
			// options here as well
		}
		return false;
	}
}
