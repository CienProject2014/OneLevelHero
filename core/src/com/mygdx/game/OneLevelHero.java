package com.mygdx.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

public class OneLevelHero extends Game {
	private ApplicationContext context;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		StaticAssets.loadAll();
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gameLoad();
	}

	public void gameLoad() {
		context.getBean(ScreenFactory.class).setGame(this);
		context.getBean(ScreenFactory.class).show(ScreenEnum.LOADING_BAR);
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
