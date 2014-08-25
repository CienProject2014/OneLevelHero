package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;
import com.mygdx.util.ScreenManager;

public class OneLevelHero extends Game {

	@Override
	public void create() {
		Assets.jsonLoad(); // 어셋을 로드해 json 파일들을 메모리에 올린다.
		Assets.characterImageLoad();
		Assets.menuScreenLoad();
		Assets.gameUILoad();
		Assets.fontLoad();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenEnum.MENU);
	}
}
