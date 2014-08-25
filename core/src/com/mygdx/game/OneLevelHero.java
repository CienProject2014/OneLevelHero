package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;
import com.mygdx.util.LoadLauncher;
import com.mygdx.util.ScreenManager;

public class OneLevelHero extends Game {
	public LoadLauncher loadLauncher;

	@Override
	public void create() {
		Assets.loadAll();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenEnum.MENU);
	}
}
