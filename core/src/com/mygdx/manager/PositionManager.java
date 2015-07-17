package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PlaceEnum;

public class PositionManager {
	@Autowired
	private PositionInfo positionInfo;

	public String getCurrentNode() {
		return positionInfo.getCurrentNode();
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
	}
}
