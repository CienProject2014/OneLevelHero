package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.manager.DungeonManager;

public class DungeonDoorButtonListener extends ClickListener {
	@Autowired
	private DungeonManager dungeonManager;

	private int index;
	@Override
	public void clicked(InputEvent event, float x, float y) {
		dungeonManager.moveRoom(index);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
