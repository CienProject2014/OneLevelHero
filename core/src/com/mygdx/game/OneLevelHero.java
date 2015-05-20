package com.mygdx.game;

import java.util.Objects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		//context = RoboSpring.getContext(); 안드로이드에서 실행시
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gameLoad();
	}

	public void gameLoad() {
		context.getBean(ScreenFactory.class).setGame(this);
		context.getBean(ScreenFactory.class).show(ScreenEnum.MENU);

		Gdx.app.log("gameLoad", "Memory_total :" + Objects.toString(Runtime.getRuntime().totalMemory() / (1024 * 1024)) + "MB");
		Gdx.app.log("gameLoad", "Memory_free :" + Objects.toString(Runtime.getRuntime().freeMemory() / (1024 * 1024)) + "MB");
		Gdx.app.log("gameLoad", "Memory_use :" + Objects.toString((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024)) + "MB");
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
