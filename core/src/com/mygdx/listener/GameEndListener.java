package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.SoundManager;

public class GameEndListener extends ClickListener {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		assetsManager.clear();
		Gdx.app.exit();
	}

}
