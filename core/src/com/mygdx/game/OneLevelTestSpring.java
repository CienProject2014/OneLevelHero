package com.mygdx.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.Assets;

public class OneLevelTestSpring extends Game {

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		Assets.loadAll();
		ApplicationContext context = new AnnotationConfigApplicationContext(
				OneLevelHeroApplicationContext.class);
		context.getBean(ScreenFactory.class).setGame(this);
		context.getBean(ScreenFactory.class).show(ScreenEnum.VILLAGE);
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