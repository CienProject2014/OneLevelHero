package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.DungeonManager;

public class LeaveDungeonButtonListener extends ClickListener {
	@Autowired
	private DungeonManager dungeonManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		dungeonManager.leaveDungeon();
		Gdx.app.log("LeaveDungeonListener", "LeaveDungeon");
	}
}
