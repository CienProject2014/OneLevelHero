package com.mygdx.listener;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.EventAssets;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.eventTrigger.EventTrigger;
import com.mygdx.factory.EventTriggerFactory;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.event.EventScene;
import com.mygdx.model.location.Building;
import com.mygdx.model.location.TargetTime;

public class BuildingButtonListener extends ClickListener {
	private static final String THE_DOOR_IS_CLOSED = "문이 굳게 닫혀있다.";
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
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private EventTriggerFactory eventTriggerFactory;
	private String nodePath;
	private String buildingPath;
	private Building buildingInfo;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (buildingInfo.getTargetTime() == null) {
			buildingInfo.setTargetTime(new TargetTime(0, 24));
		}
		eventManager.setTargetBuildingInfo(buildingInfo);
		EventTrigger eventTrigger = eventTriggerFactory.getEventTrigger(EventTypeEnum.GO_SUB_NODE);
		eventTrigger.triggerEvent(getGoSubNodeEventParameter(buildingInfo));
		if (buildingInfo.canGoBuilding(timeManager.getDayMinute())) {
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE, nodePath, buildingPath);
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE_BY_TIME, buildingPath);
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE_BEFORE_ABSOLUTE_TIME, buildingPath);
			storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE_AFTER_ABSOLUTE_TIME, buildingPath);
		}
		timeManager.plusMinute(15); // 건물에 들어가는데 15분
	}

	private EventParameters getGoSubNodeEventParameter(Building buildingInfo) {
		EventParameters eventParameter = new EventParameters();
		ArrayList<EventScene> eventScenes = new ArrayList<>();
		eventScenes.add(new EventScene(buildingInfo.getOuterPath(), "nothing_image", THE_DOOR_IS_CLOSED, "01"));
		eventParameter.setEventScenes(eventScenes);
		return eventParameter;
	}
	public Building getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(Building buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	public String getBuildingPath() {
		return buildingPath;
	}

	public void setBuildingPath(String buildingName) {
		this.buildingPath = buildingName;
	}
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodeName) {
		this.nodePath = nodeName;
	}

}
