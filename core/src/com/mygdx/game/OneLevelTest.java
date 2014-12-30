package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.ScreenManager;

public class OneLevelTest extends Game {

	@Override
	public void create() {
		LoadManager.getInstance();
		ScreenManager.getInstance().initialize(this);
		new ScreenController(ScreenEnum.WORLD_MAP);
	}

}
