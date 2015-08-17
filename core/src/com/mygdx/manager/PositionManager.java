package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.enums.WorldNodeEnum.NodeType;
import com.mygdx.factory.ScreenFactory;

public class PositionManager {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private PositionInfo positionInfo;

	public PositionEnum getBeforePositionType() {
		return positionInfo.getBeforePositionType();
	}

	public void setBeforePositionType(PositionEnum beforePositionType) {
		positionInfo.setBeforePositionType(beforePositionType);
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

	public PositionEnum getCurrentPositionType() {
		return positionInfo.getCurrentPositionType();
	}

	public void setCurrentNodeName(String currentNodeName) {
		Gdx.app.log("PositionManager", "현재노드 이름 - " + currentNodeName);
		setCurrentPositionType(PositionEnum.NODE);
		positionInfo.setCurrentNodeName(currentNodeName);
	}

	public String getCurrentNodeHanguelName() {
		return worldMapAssets.getWorldNodeInfo(getCurrentNodeName()).getNodeName();
	}

	public void setCurrentPositionType(PositionEnum positionEnum) {
		Gdx.app.log("PositionManager", "현재위치 - " + positionEnum.toString());
		positionInfo.setCurrentPositionType(positionEnum);
	}

	public String getCurrentSubNodeName() {
		return positionInfo.getCurrentSubNodeName();
	}

	public String getCurrentSubNodeHanguelName() {
		if (getCurrentNodeType().equals(NodeType.VILLAGE)) {
			return nodeAssets.getVillage(getCurrentNodeName()).getBuilding().get(getCurrentSubNodeName())
					.getBuildingName();
		} else {
			return "던젼"; // FIXME
		}

	}

	public void setCurrentSubNodeName(String subNodeName) {
		Gdx.app.log("PositionManager", "현재서브노드 이름 - " + subNodeName);
		positionInfo.setCurrentPositionType(PositionEnum.SUB_NODE);
		positionInfo.setCurrentSubNodeName(subNodeName);
	}

}
