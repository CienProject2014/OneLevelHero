package com.mygdx.currentState;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.enums.PlaceEnum;

public class PositionInfo {
	@Autowired
	private WorldMapAssets worldMapAssets;
	private PlaceEnum currentPlace; // 플레이어가 마을인지, 갈림길인지, 던전인지, 건물안인지
	private String currentNode; // 최근 마을/던전/교차로 이름
	private String currentBuilding;

	public PositionInfo() {
	}

	public PlaceEnum getCurrentNodeType() {
		return worldMapAssets.getWorldNodeType(currentNode);
	}

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	public String getCurrentBuilding() {
		return currentBuilding;
	}

	public void setCurrentBuilding(String currentBuilding) {
		this.currentBuilding = currentBuilding;
	}

	public PlaceEnum getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(PlaceEnum currentPlace) {
		this.currentPlace = currentPlace;
	}
}
