package com.mygdx.listener;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.location.NodeConnection;

public class ArrowButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private MovingManager movingManager;
	private Entry<String, NodeConnection> connection;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		soundManager.setSoundByPathAndPlay("notice_moving");
		int beforeSectionNumber = storySectionManager.getCurrentStorySectionNumber();
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.CLICK_ARROW, connection.getValue().getArrowName());
		int currentSectionNumber = storySectionManager.getCurrentStorySectionNumber();
		if (beforeSectionNumber != currentSectionNumber) {
			return;
		}
		fieldManager.startMovingField(connection.getKey());
		if (fieldManager.getFieldLength() == 0) {
			fieldManager.goForwardField();
		} else {
			positionManager.setCurrentLocatePositionType(PositionEnum.LocatePosition.FIELD);
			screenFactory.show(ScreenEnum.FIELD);
			fieldManager.goInField();
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_FIELD, connection.getValue().getArrowName());
		}
		if (!fieldManager.isInField()) {
			String node = fieldManager.getDestinationNode();
			movingManager.goToNode(node);
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_NODE, node);
		}
		soundManager.setSoundByUseAndPlay("move_arrow");
		timeManager.plusMinute(30); // 마을에서 길로 나가는데 30분
	}
	public Entry<String, NodeConnection> getConnection() {
		return connection;
	}

	public void setConnection(Entry<String, NodeConnection> connection) {
		this.connection = connection;
	}
}
