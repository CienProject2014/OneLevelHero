package com.mygdx.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.StaticAssets;

@Component
public class OneLevelHero extends Game {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private ApplicationContext context;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		StaticAssets.loadAll();
		context = new AnnotationConfigApplicationContext(
				OneLevelHeroApplicationContext.class);
		Gdx.app.debug("OneLevelHero", "ComponentScan 끝");
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