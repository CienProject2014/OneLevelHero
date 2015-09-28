package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.SoundManager;
import com.mygdx.screen.BaseScreen;

public class LoadListener extends ClickListener {
	@Autowired
	private SoundManager soundManager;
	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		BaseScreen.showLoadStage = true;
	}

}
