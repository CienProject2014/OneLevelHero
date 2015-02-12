package com.mygdx.currentState;

import com.mygdx.enums.PlaceEnum;


public class PositionInfo {
	private PlaceEnum currentPlace; //마을인지, 갈림길인지, 던전인지, 건물안인지
	private String currentNode; //현재 마을/던전/교차로 이름	
	private String currentBuilding;

	public PositionInfo() {
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
