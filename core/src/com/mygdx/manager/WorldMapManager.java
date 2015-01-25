package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorldMapManager {
	@Autowired
	MovingInfoManager movingInfoManager;

	//목적 노드 결정
	public void selectDestinationNode(String destinationNode) {
		movingInfoManager.createMovingInfo(destinationNode);
	}
}