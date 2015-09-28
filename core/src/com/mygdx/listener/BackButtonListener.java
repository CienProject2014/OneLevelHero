package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.screen.StatusScreen;

public class BackButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		timeManager.plusMinute(15);
		if (!positionManager.isInWorldMap()) {
			movingManager.goPreviousPosition();
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_NODE, positionManager.getCurrentNodePath());
		} else {
			if (StatusScreen.isClickedWorldMap()) {
				StatusScreen.setClickedWorldMap(false);
				positionManager.setInWorldMap(false);
				screenFactory.show(ScreenEnum.STATUS);
			} else {
				movingManager.goCurrentLocatePosition();
			}
		}
	}
}