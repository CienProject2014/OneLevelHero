package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
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
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		fieldManager.goForwardField();
		timeManager.plusMinute(30);
		if (!fieldManager.isInField()) {
			String node = fieldManager.getDestinationNode();
			soundManager.setSoundByUseAndPlay("move_arrow");
			movingManager.goToNode(node);
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_NODE, node);
		}
	}
}
