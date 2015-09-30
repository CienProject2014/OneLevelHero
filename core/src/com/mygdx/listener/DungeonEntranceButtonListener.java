package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;

public class DungeonEntranceButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private SoundManager soundManager;
	private String dungeonPath;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		soundManager.setSoundByPathAndPlay("notice_moving");
		timeManager.plusMinute(15);
		soundManager.playClickSound();
		positionManager.setCurrentSubNodePath(dungeonPath);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE, dungeonPath);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE_BY_TIME, dungeonPath);
		positionManager.setCurrentLocatePositionType(PositionEnum.LocatePosition.DUNGEON);
		screenFactory.show(ScreenEnum.DUNGEON);
	}
	public String getDungeonPath() {
		return dungeonPath;
	}

	public void setDungeonPath(String dungeonPath) {
		this.dungeonPath = dungeonPath;
	}
}
