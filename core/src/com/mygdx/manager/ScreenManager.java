package com.mygdx.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.mygdx.Factory.StageFactory;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;

public final class ScreenManager  {

	private static ScreenManager instance;

	private static Game game;

	private IntMap<Screen> screens;
	

	private ScreenManager() {
		screens = new IntMap<Screen>();
	}

	public static ScreenManager getInstance() {
		if (null == instance) {
			instance = new ScreenManager();
		}
		return instance;
	}

	public void initialize(Game game) {
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

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		ScreenManager.game = game;
	}
}
