package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.PositionInfo;
import com.mygdx.model.WorldNode;
import com.mygdx.state.Assets;

public class WorldMapManager {
	@Autowired
	private MovingInfoManager movingInfoManager;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private Assets assets;

	//목적 노드 결정
	public void selectDestinationNode(String destinationNode) {
		WorldNode worldNodeInfo = assets.worldNodeInfoMap.get(positionInfo
				.getCurrentNode());
		movingInfoManager.createMovingInfo(destinationNode, worldNodeInfo);
	}

	public MovingInfoManager getMovingInfoManager() {
		return movingInfoManager;
	}

	public void setMovingInfoManager(MovingInfoManager movingInfoManager) {
		this.movingInfoManager = movingInfoManager;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

}