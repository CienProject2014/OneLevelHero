package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.enums.TextureEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.manager.TextureManager;

/**
 * Village 모델 클래스
 * @author Velmont
 *
 */
public class Village implements Serializable {
	private String villageName;
	private String backgroundPath;
	private List<String> villageNpc;
	private Map<String, Building> building;

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Texture getBackgroundUp() {
		return TextureManager.getBackgroundTexture(backgroundPath,
				TextureEnum.BACKGROUND_UP);
	}

	public Texture getBackgroundDown() {
		return TextureManager.getBackgroundTexture(backgroundPath,
				TextureEnum.BACKGROUND_DOWN);
	}

	public List<String> getVillageNpc() {
		return villageNpc;
	}

	public void setVillageNpc(List<String> villageNpc) {
		this.villageNpc = villageNpc;
	}

	public Map<String, Building> getBuilding() {
		return building;
	}

	public void setBuilding(Map<String, Building> building) {
		this.building = building;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		villageName = json.readValue("villageName", String.class, jsonData);
		backgroundPath = json.readValue("backgroundPath", String.class,
				jsonData);
		villageNpc = json.readValue("villageNpc", ArrayList.class,
				String.class, jsonData);
		building = JsonParser.parseMap(Building.class, jsonData.get("building")
				.toString());
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}
}
