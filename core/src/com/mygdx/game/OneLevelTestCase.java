package com.mygdx.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.StaticAssets;

public class OneLevelTestCase extends Game {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private ApplicationContext context;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		//디버그용 로그도 보이도록 설정
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		StaticAssets.loadAll();
		context = new AnnotationConfigApplicationContext(
				OneLevelHeroApplicationContext.class);
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