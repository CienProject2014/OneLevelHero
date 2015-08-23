package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.BattleManager;

public class CloseButtonListener extends ClickListener {
	@Autowired
	BattleManager battleManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		battleManager.showRMenuButtons();
		battleManager.checkCurrentState();
	}
}
