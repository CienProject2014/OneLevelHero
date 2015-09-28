package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.DungeonEnum;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.TimeManager;

public class DungeonStairButtonListener extends ClickListener {
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private SoundManager soundManager;
	private DungeonEnum.Type stairType;
	private String link;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		dungeonManager.moveStair(stairType, link);
		timeManager.plusMinute(15);
	}
	public DungeonEnum.Type getStairType() {
		return stairType;
	}

	public void setStairType(DungeonEnum.Type stairType) {
		this.stairType = stairType;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
