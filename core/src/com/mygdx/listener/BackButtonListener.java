package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;

public class BackButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (!positionManager.isInWorldMap()) {
			movingManager.goPreviousPosition();
		} else {
			movingManager.goCurrentPosition();
		}
		storySectionManager.triggerSectionEvent(EventTypeEnum.MOVE_NODE,
				positionManager.getCurrentNodeName());
	}
}