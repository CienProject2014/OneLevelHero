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
		return worldMapAssets.getNodeType(positionInfo.getCurrentNodeName());
	}

	public WorldNodeEnum.NodeType getNodeType(String nodeName) {
		return worldMapAssets.getNodeType(nodeName);
	}

	public String getCurrentNodeName() {
		return positionInfo.getCurrentNodeName();
	}

	public PositionEnum.LocatePosition getCurrentLocatePositionType() {
		return positionInfo.getCurrentLocatePositionType();
	}

	public void setCurrentNodeName(String currentNodeName) {
		Gdx.app.log("PositionManager", "현재노드 이름 - " + currentNodeName);
		setCurrentLocatePositionType(PositionEnum.LocatePosition.NODE);
		positionInfo.setCurrentNodeName(currentNodeName);
	}

	public String getCurrentNodeHanguelName() {
		return worldMapAssets.getWorldNodeInfo(getCurrentNodeName()).getNodeName();
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

	public String getCurrentSubNodeName() {
		return positionInfo.getCurrentSubNodeName();
	}

	public String getCurrentSubNodeHanguelName() {
		if (getCurrentNodeType().equals(NodeType.VILLAGE)) {
			return nodeAssets.getVillageByName(getCurrentNodeName()).getBuilding().get(getCurrentSubNodeName())
					.getSubNodeName();
		} else {
			return "던젼"; // FIXME
		}

	}
	public void setCurrentSubNodeName(String subNodeName) {
		Gdx.app.log("PositionManager", "현재서브노드 이름 - " + subNodeName);
		positionInfo.setCurrentLocatePositionType(PositionEnum.LocatePosition.SUB_NODE);
		positionInfo.setCurrentSubNodeName(subNodeName);
	}

	public boolean isInWorldMap() {
		return inWorldMap;
	}

	public void setInWorldMap(boolean inWorldMap) {
		this.inWorldMap = inWorldMap;
	}
}
