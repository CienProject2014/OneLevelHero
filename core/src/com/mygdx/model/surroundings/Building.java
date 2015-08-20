package com.mygdx.model.surroundings;

import java.util.List;

import com.mygdx.enums.BuildingTypeEnum;

/**
 * 마을 내부에 존재하는 건물
 * 
 * @author Velmont
 * 
 */
public class Building {
	private String buildingName;
	private BuildingTypeEnum buildingType;
	private String buildingPath;
	private List<String> buildingNpc;
	private List<String> gameObject;
	private String sceneName;
	private boolean isOverlapScene;

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public BuildingTypeEnum getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingTypeEnum buildingType) {
		this.buildingType = buildingType;
	}

	public String getBuildingPath() {
		return buildingPath;
	}

	public void setBuildingPath(String buildingPath) {
		this.buildingPath = buildingPath;
	}

	public List<String> getBuildingNpc() {
		return buildingNpc;
	}

	public void setBuildingNpc(List<String> buildingNpc) {
		this.buildingNpc = buildingNpc;
	}
	public List<String> getGameObject() {
		return gameObject;
	}

	public void setGameObject(List<String> gameObject) {
		this.gameObject = gameObject;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public boolean isOverlapScene() {
		return isOverlapScene;
	}

	public void setOverlapScene(boolean isOverlapScene) {
		this.isOverlapScene = isOverlapScene;
	}
}
