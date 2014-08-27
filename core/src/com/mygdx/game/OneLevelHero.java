package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;
import com.mygdx.util.LoadLauncher;
import com.mygdx.util.ScreenManager;

public class OneLevelHero extends Game {
	public LoadLauncher loadLauncher;

	@Override
	public void create() {
		Assets.loadAll();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenEnum.MENU);
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
