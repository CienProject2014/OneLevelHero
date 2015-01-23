package com.mygdx.manager;

import com.mygdx.model.CurrentPosition;
import com.mygdx.model.CurrentPosition.CurrentMovingInfo;
import com.mygdx.model.WorldNodeInfo;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

/**
 * WorldMapScreen에서 목적지 노드를 선택할 시에 CurrentMovingInfo를 설정해주는 클래스.
 * 
 * @author Velmont
 * 
 */
public class CurrentMovingManager {
	private static CurrentMovingInfo currentMovingInfo = CurrentState
			.getInstance().getCurrentPosition().getCurrentMovingInfo();
	private static CurrentPosition currentPosition = CurrentState.getInstance()
			.getCurrentPosition();
	private static WorldNodeInfo worldNodeInfo = Assets.worldNodeInfoMap
			.get(currentPosition.getCurrentNode());

	/**
	 * 월드맵 정보에서 roadLength, startNode, destination, roadMonster 정보를
	 * CurrentState의 CurrentMovingInfo에 전달한다.
	 */
	public static void createCurrentMovingInfo(String destinationNode) {
		currentMovingInfo.setStartNode(CurrentState.getInstance()
				.getVillageInfo().getCurrentPosition());
		currentMovingInfo.setDestinationNode(destinationNode);
		currentMovingInfo.setRoadLength(worldNodeInfo.getConnection()
				.get(destinationNode).getroadLength());
		currentMovingInfo.setLeftRoadLength(worldNodeInfo.getConnection()
				.get(destinationNode).getroadLength());
		currentMovingInfo.setRoadMonsterList(worldNodeInfo.getConnection()
				.get(destinationNode).getRoadMonster());
	}

}
