package com.mygdx.manager;

public class WorldMapManager {

	//목적 노드 결정
	public static void selectDestinationNode(String destinationNode) {
		CurrentMovingManager.createCurrentMovingInfo(destinationNode);
	}
}