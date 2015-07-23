package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

public class PositionManager {
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private ScreenFactory screenFactory;

	public String getCurrentNode() {
		return positionInfo.getCurrentNode();
	}

	public PlaceEnum getCurrentNodeType() {
		return positionInfo.getCurrentNodeType();
	}

	public void goCurrentPlace() {
		setCurrentPlace(getCurrentNodeType());
		screenFactory.show(ScreenEnum.findScreenEnum(getCurrentNodeType()
				.toString()));
	}

	public void setCurrentNode(String currentNode) {
		positionInfo.setCurrentNode(currentNode);
	}

	public String getCurrentBuilding() {
		return positionInfo.getCurrentBuilding();
	}

	public void setCurrentBuilding(String currentBuilding) {
		positionInfo.setCurrentBuilding(currentBuilding);
	}

	public PlaceEnum getCurrentPlace() {
		return positionInfo.getCurrentPlace();
	}

	public void setCurrentPlace(PlaceEnum currentPlace) {
		positionInfo.setCurrentPlace(currentPlace);
		Gdx.app.log("PositionManager", "현재위치 : " + currentPlace);
	}
}
