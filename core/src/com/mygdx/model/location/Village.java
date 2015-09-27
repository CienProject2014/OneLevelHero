package com.mygdx.model.location;

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
	private String nodeName;
	private String nodePath;
	private String scenePath;
	private HashMap<String, VillageDirectionEnum> arrowDirection;
	private VillageDirectionEnum villageDirection;
	private ArrayList<String> villageNpc;
	private HashMap<String, Building> building;

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

	public VillageDirectionEnum getVillageDirection() {
		return villageDirection;
	}

	public void setVillageDirection(VillageDirectionEnum villageDirection) {
		this.villageDirection = villageDirection;
	}

	public HashMap<String, VillageDirectionEnum> getArrowDirection() {
		return arrowDirection;
	}

	public void setArrowDirection(HashMap<String, VillageDirectionEnum> arrowDirection) {
		this.arrowDirection = arrowDirection;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public String getScenePath() {
		return scenePath;
	}

	public void setScenePath(String scenePath) {
		this.scenePath = scenePath;
	}
}
