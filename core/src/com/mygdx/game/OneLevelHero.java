package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.EventTrigger;
import com.mygdx.resource.Assets;
import com.mygdx.resource.ResourceFile;
import com.mygdx.util.CurrentManager;
import com.mygdx.util.EventManager;
import com.mygdx.util.LoadLauncher;
import com.mygdx.util.ScreenManager;
import com.mygdx.util.SoundManager;

public class OneLevelHero extends Game {
	SpriteBatch batch;
	Texture img;
	public LoadLauncher loadLauncher;
	public CurrentManager currentManager;
	public SoundManager soundManager;
	public EventManager eventManager;
	public EventTrigger eventTrigger;
	public ResourceFile resourceFile;

	@Override
	public void create() {
		Assets.loadAll(); // 어셋을 로드해 json 파일들을 메모리에 올린다.
		this.currentManager = new CurrentManager();
		this.soundManager = new SoundManager();
		this.eventManager = new EventManager();
		this.eventTrigger = new EventTrigger();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenEnum.MENU);
	}
}
