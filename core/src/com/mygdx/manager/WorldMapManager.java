package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.model.WorldNode;

public class WorldMapManager {
	@Autowired
	private MovingInfoManager movingInfoManager;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private WorldMapAssets worldMapAssets;

	// 목적 노드 결정
	public void selectDestinationNode(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets.getWorldNodeInfo(positionInfo
				.getCurrentNode());
		movingInfoManager.createMovingInfo(destinationNode, worldNodeInfo);
	}
}
