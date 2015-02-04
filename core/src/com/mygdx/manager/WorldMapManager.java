package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.currentState.PositionInfo;
import com.mygdx.model.WorldNode;
import com.mygdx.state.Assets;

@Component
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
}