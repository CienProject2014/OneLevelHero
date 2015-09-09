package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.location.Building;
import com.mygdx.model.location.TargetTime;

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
	private String nodeName;
	private String buildingName;
	private Building buildingInfo;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (buildingInfo.getTargetTime() != null) {
			EventPacket eventPacket = new EventPacket(buildingName, 1);
			eventManager.setTargetBuildingInfo(buildingInfo);
			eventManager.triggerEvent(EventElementEnum.NPC, eventPacket);
		} else {
			TargetTime targetTime = new TargetTime(0, 0);
			buildingInfo.setTargetTime(targetTime);
			EventPacket eventPacket = new EventPacket(buildingName, 1);
			eventManager.setTargetBuildingInfo(buildingInfo);
			eventManager.triggerEvent(EventElementEnum.NPC, eventPacket);
		}
		timeManager.plusMinute(15); // 건물에 들어가는데 15분
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE, nodeName, buildingName);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.MOVE_SUB_NODE_BY_TIME, buildingName);
	}
	public Building getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(Building buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

}
