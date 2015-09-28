package com.mygdx.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

public class OneLevelHero extends Game {
	private ApplicationContext context;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean(ScreenFactory.class).setGame(this);
		context.getBean(ScreenFactory.class).popAllAndPush(ScreenEnum.LOADING_BAR);
	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
		}
		return false;
	}
}
