package com.mygdx.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.screen.BaseScreen;

public class LoadListener extends ClickListener {

	@Override
	public void clicked(InputEvent event, float x, float y) {
		BaseScreen.showLoadStage = true;
	}

}
