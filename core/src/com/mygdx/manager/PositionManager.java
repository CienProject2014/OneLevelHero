package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;

public class PositionManager {
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private WorldMapAssets worldMapAssets;

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
		Gdx.app.log("PositionManager", "현재위치 : " + currentNodeName);
		setCurrentPositionType(PositionEnum.NODE);
		positionInfo.setCurrentNodeName(currentNodeName);
	}

	public void setCurrentPositionType(PositionEnum positionEnum) {
		positionInfo.setCurrentPositionType(positionEnum);
	}

	public String getCurrentSubNodeName() {
		return positionInfo.getCurrentSubNodeName();
	}

	public void setCurrentSubNodeName(String subNodeName) {
		Gdx.app.log("PositionManager", "현재위치 : " + subNodeName);
		positionInfo.setCurrentPositionType(PositionEnum.SUB_NODE);
		positionInfo.setCurrentSubNodeName(subNodeName);
	}
}
