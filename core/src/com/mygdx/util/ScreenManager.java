package com.mygdx.util;

import com.badlogic.gdx.utils.IntMap;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.game.OneLevelHero;

public final class ScreenManager {

	private static ScreenManager instance;

	private static OneLevelHero game;

	private IntMap<com.badlogic.gdx.Screen> screens;

	private ScreenManager() {
		screens = new IntMap<com.badlogic.gdx.Screen>();
	}

	public static ScreenManager getInstance() {
		if (null == instance) {
			instance = new ScreenManager();
		}
		return instance;
	}

	public void initialize(OneLevelHero game) {
		ScreenManager.setGame(game);
	}

	public void show(ScreenEnum screen) {
		if (null == getGame())
			return;
		if (!screens.containsKey(screen.ordinal())) {
			screens.put(screen.ordinal(), screen.getScreenInstance());
		}
		getGame().setScreen(screens.get(screen.ordinal()));
	}

	public void dispose(ScreenEnum screen) {
		if (!screens.containsKey(screen.ordinal()))
			return;
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose() {
		for (com.badlogic.gdx.Screen screen : screens.values()) {
			screen.dispose();
		}
		screens.clear();
		instance = null;
	}

	public static OneLevelHero getGame() {
		return game;
	}

	public static void setGame(OneLevelHero game) {
		ScreenManager.game = game;
	}
}
