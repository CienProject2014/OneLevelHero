package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.enums.BuildingTypeEnum;

/**
 * 마을 내부에 존재하는 건물
 * 
 * @author Velmont
 *
 */
public class Building implements Serializable {
	private String buildingName;
	private BuildingTypeEnum buildingType;
	private String buildingPath;
	private List<String> buildingNpc;
	private String sceneName;

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

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		buildingName = json.readValue("buildingName", String.class, jsonData);
		buildingType = json.readValue("buildingType", BuildingTypeEnum.class, jsonData);
		buildingPath = json.readValue("buildingPath", String.class, jsonData);
		buildingNpc = json.readValue("buildingNpc", ArrayList.class, String.class, jsonData);
		sceneName = json.readValue("sceneName", String.class, jsonData);
	}

	@Override
	public void write(Json json) {}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
}
