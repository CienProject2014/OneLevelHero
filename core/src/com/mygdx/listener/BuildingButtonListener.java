package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;

public class BuildingButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private ScreenFactory screenFactory;
	private String buildingName;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
		positionManager.setCurrentSubNodeName(buildingName);
		screenFactory.show(ScreenEnum.BUILDING);
		storySectionManager.triggerSectionEvent(EventTypeEnum.MOVE_SUB_NODE,
				buildingName);
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
