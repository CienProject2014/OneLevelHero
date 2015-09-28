package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;

public class LeaveEventElementListener extends ClickListener {
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		positionManager.setCurrentEventPositionType(EventPosition.NONE);
		movingManager.goCurrentLocatePosition();
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.COLLECT_EVENT);
	}
}
