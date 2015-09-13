package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.VillageDirectionEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.enums.WorldNodeEnum.NodeType;

public class PositionManager {
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private PositionInfo positionInfo;
	private boolean inWorldMap;

	public VillageDirectionEnum getVillageDirection() {
		return positionInfo.getVillageDirection();
	}

	public void setVillageDirection(VillageDirectionEnum villageDirection) {
		positionInfo.setVillageDirection(villageDirection);
	}
	public WorldNodeEnum.NodeType getCurrentNodeType() {
		return worldMapAssets.getNodeType(positionInfo.getCurrentNodePath());
	}

	public WorldNodeEnum.NodeType getNodeType(String nodePath) {
		return worldMapAssets.getNodeType(nodePath);
	}

	public String getCurrentNodePath() {
		return positionInfo.getCurrentNodePath();
	}

	public PositionEnum.LocatePosition getCurrentLocatePositionType() {
		return positionInfo.getCurrentLocatePositionType();
	}

	public void setCurrentNodePath(String currentNodePath) {
		Gdx.app.log("PositionManager", "현재노드 경로 - " + currentNodePath);
		setCurrentLocatePositionType(PositionEnum.LocatePosition.NODE);
		positionInfo.setCurrentNodePath(currentNodePath);
	}

	public String getCurrentNodeName() {
		return worldMapAssets.getWorldNodeInfo(getCurrentNodePath()).getNodeName();
	}

	public void setCurrentLocatePositionType(PositionEnum.LocatePosition positionEnum) {
		Gdx.app.log("PositionManager", "현재위치 - " + positionEnum.toString());
		positionInfo.setCurrentLocatePositionType(positionEnum);
	}

	public void setCurrentEventPositionType(PositionEnum.EventPosition eventPosition) {
		positionInfo.setCurrentEventPositionType(eventPosition);
	}

	public PositionEnum.EventPosition getCurrentEventPositionType() {
		return positionInfo.getCurrentEventPositionType();
	}

	public String getCurrentSubNodePath() {
		return positionInfo.getCurrentSubNodePath();
	}

	public String getCurrentSubNodeName() {
		if (getCurrentNodeType().equals(NodeType.VILLAGE)) {
			return nodeAssets.getVillageByPath(getCurrentNodePath()).getBuilding().get(getCurrentSubNodePath())
					.getSubNodeName();
		} else {
			return "던젼"; // FIXME
		}

	}
	public void setCurrentSubNodePath(String subNodePath) {
		Gdx.app.log("PositionManager", "현재서브노드 경로 - " + subNodePath);
		positionInfo.setCurrentLocatePositionType(PositionEnum.LocatePosition.SUB_NODE);
		positionInfo.setCurrentSubNodePath(subNodePath);
	}

	public boolean isInWorldMap() {
		return inWorldMap;
	}

	public void setInWorldMap(boolean inWorldMap) {
		this.inWorldMap = inWorldMap;
	}
}
