package com.mygdx.game;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.ScreenManager;

@Configuration
@ComponentScan
public class OneLevelHeroSpring extends Game {

	@Override
	public void create() {

		//		ApplicationContext context = new AnnotationConfigApplicationContext(
		//				OneLevelHeroSpring.class);

		LoadManager.getInstance();
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