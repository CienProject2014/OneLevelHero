package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;

public class BuildingButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private TimeManager timeManager;
	private String buildingName;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		timeManager.plusMinute(15); // 건물에 들어가는데 15분
		positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
		positionManager.setCurrentSubNodeName(buildingName);
		screenFactory.show(ScreenEnum.BUILDING);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE, buildingName);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE_BY_TIME, buildingName);
		eventManager.triggerSpecialEvent(EventTypeEnum.DONT_GO_BUILDING, buildingName);

	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
