package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;

public class GoForwardFieldButtonListener extends ClickListener {
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		fieldManager.goForwardField();
		storySectionManager.triggerSectionEvent(EventTypeEnum.MOVE_NODE,
				positionManager.getCurrentNodeName());
	}
}
