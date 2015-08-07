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
import com.mygdx.model.surroundings.Connection;

public class ArrowButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private PositionManager positionManager;
	private Entry<String, Connection> connection;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		fieldManager.selectDestinationNode(connection.getKey());
		positionManager.setCurrentPositionType(PositionEnum.FIELD);
		screenFactory.show(ScreenEnum.FIELD);
		storySectionManager.triggerSectionEvent(EventTypeEnum.MOVE_FIELD,
				connection.getValue().getArrowName());
	}

	public Entry<String, Connection> getConnection() {
		return connection;
	}

	public void setConnection(Entry<String, Connection> connection) {
		this.connection = connection;
	}
}
