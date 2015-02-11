package com.mygdx.game;

import org.robospring.RoboSpring;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.StaticAssets;

public class OneLevelHero extends Game {
	private ApplicationContext context;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		StaticAssets.loadAll();
		context = RoboSpring.getContext();
		gameLoad();
	}

	public void gameLoad() {
		context.getBean(ScreenFactory.class).setGame(this);
		context.getBean(ScreenFactory.class).show(ScreenEnum.MENU);
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