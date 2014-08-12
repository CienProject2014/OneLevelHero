package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.resource.Assets;
import com.mygdx.resource.CurrentManager;
import com.mygdx.resource.LoadLauncher;
import com.mygdx.screen.MenuScreen;

public class OneLevelHero extends Game {
	SpriteBatch batch;
	Texture img;
	public LoadLauncher loadLauncher;
	public CurrentManager currentManager;

	@Override
	public void create() {
		Assets.jsonLoad(); //어셋을 로드해 json 파일들을 메모리에 올린다.
		currentManager = new CurrentManager();
		setScreen(new MenuScreen(this));
	}
}
