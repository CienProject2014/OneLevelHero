package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.SoundManager;

public class CloseButtonListener extends ClickListener {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		battleManager.getBattleFlag().setMonsterTurnEnd(true);
		battleManager.setBattleCommandButtonClickState();
	}
}
