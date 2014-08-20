package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.event.EventTrigger;
import com.mygdx.resource.Assets;
import com.mygdx.screen.MenuScreen;
import com.mygdx.util.CurrentManager;
import com.mygdx.util.EventManager;
import com.mygdx.util.LoadLauncher;
import com.mygdx.util.SoundManager;

public class OneLevelHero extends Game {
	SpriteBatch batch;
	Texture img;
	public LoadLauncher loadLauncher;
	public CurrentManager currentManager;
	public SoundManager soundManager;
	public EventManager eventManager;
	public EventTrigger eventTrigger;

	@Override
	public void create() {
		Assets.jsonLoad(); //어셋을 로드해 json 파일들을 메모리에 올린다.
		currentManager = new CurrentManager();
		soundManager = new SoundManager();
		eventManager = new EventManager();
		setScreen(new MenuScreen(this));
	}
}
