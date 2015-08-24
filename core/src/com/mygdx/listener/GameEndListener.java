package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.AssetsManager;

public class GameEndListener extends ClickListener {
	@Autowired
	AssetsManager assetsManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		assetsManager.clear();
		Gdx.app.exit();
	}

}
