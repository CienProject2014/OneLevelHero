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
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.surroundings.NodeConnection;

public class ArrowButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private PositionManager positionManager;
	private Entry<String, NodeConnection> connection;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		int beforeSectionNumber = storySectionManager.getCurrentStorySectionNumber();
		storySectionManager.triggerSectionEvent(EventTypeEnum.CLICK_ARROW, connection.getValue().getArrowName());
		int currentSectionNumber = storySectionManager.getCurrentStorySectionNumber();
		if (beforeSectionNumber != currentSectionNumber) {
			return;
		}
		fieldManager.startMovingField(connection.getKey());
		if (fieldManager.getFieldLength() == 0) {
			fieldManager.goForwardField();
		} else {
			positionManager.setCurrentPositionType(PositionEnum.FIELD);
			screenFactory.show(ScreenEnum.FIELD);
			fieldManager.goInField();
			storySectionManager.triggerSectionEvent(EventTypeEnum.MOVE_FIELD, connection.getValue().getArrowName());
		}
	}

	public Entry<String, NodeConnection> getConnection() {
		return connection;
	}

	public void setConnection(Entry<String, NodeConnection> connection) {
		this.connection = connection;
	}
}
