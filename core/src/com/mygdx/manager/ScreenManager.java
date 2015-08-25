package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.screen.BaseScreen;

public class ScreenManager {
	@Autowired
	private ScreenFactory screenFactory;
	private BaseScreen currentScreen;

	public BaseScreen getCurrentScreen() {
		return screenFactory.getScreens().get(ScreenEnum.BATTLE.ordinal());
	}

	public BaseScreen getScreen(ScreenEnum screenEnum) {
		return screenFactory.getScreens().get(screenEnum.ordinal());
	}
}
