package com.mygdx.model.surroundings;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.enums.VillageDirectionEnum;

/**
 * Village 모델 클래스
 * 
 * @author Velmont
 * 
 */
public class Village {
	private String villageName;
	private String backgroundPath;
	private String sceneName;
	private VillageDirectionEnum villageDirection;
	private ArrayList<String> villageNpc;
	private HashMap<String, Building> building;

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public ArrayList<String> getVillageNpc() {
		return villageNpc;
	}

	public void setVillageNpc(ArrayList<String> villageNpc) {
		this.villageNpc = villageNpc;
	}

	public HashMap<String, Building> getBuilding() {
		return building;
	}

	public void setBuilding(HashMap<String, Building> building) {
		this.building = building;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public VillageDirectionEnum getVillageDirection() {
		return villageDirection;
	}

	public void setVillageDirection(VillageDirectionEnum villageDirection) {
		this.villageDirection = villageDirection;
	}
}
