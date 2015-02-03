package com.mygdx.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.Assets;

@Component
public class OneLevelHeroSpring extends Game {
	@Autowired
	@Qualifier("game")
	private Assets assets;
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		ApplicationContext context = new AnnotationConfigApplicationContext(
				OneLevelHeroApplicationContext.class);
		gameLoad();
	}

	public void gameLoad() {
		assets.loadAll();
		screenFactory.setGame(this);
		screenFactory.show(ScreenEnum.MENU);
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