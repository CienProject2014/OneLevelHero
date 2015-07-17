package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.model.WorldNode;

public class WorldMapManager {
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private WorldMapAssets worldMapAssets;

	// 목적 노드 결정
	public void selectDestinationNode(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets
				.getWorldNodeInfo(positionManager.getCurrentNode());
		movingManager.createMovingInfo(destinationNode, worldNodeInfo);
	}
}
