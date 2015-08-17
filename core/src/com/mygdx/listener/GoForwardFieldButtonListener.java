package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;

public class GoForwardFieldButtonListener extends ClickListener {
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private MovingManager movingManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		FieldInfo fieldInfo = fieldManager.goFowardAndGetFieldInfo();
		if (!fieldInfo.isInField()) {
			String node = fieldInfo.getDestinationNode();
			movingManager.goToNode(node);
			storySectionManager.triggerSectionEvent(EventTypeEnum.MOVE_NODE,
					node);
		}

		timeManager.plusMinute(30);
	}
}
