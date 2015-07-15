package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.Assets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.model.WorldNode;

/**
 * WorldMapScreen에서 목적지 노드를 선택할 시에 movingInfo를 설정해주는 클래스.
 *
 * @author Velmont
 *
 */
public class MovingInfoManager {
	@Autowired
	private Assets assets;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PositionInfo positionInfo;

	/**
	 * 월드맵 정보에서 roadLength, startNode, destination, roadMonster 정보를
	 * CurrentState의 movingInfo에 전달한다.
	 */
	public void createMovingInfo(String destinationNode, WorldNode worldNodeInfo) {
		movingInfo.setStartNode(positionInfo.getCurrentNode());
		movingInfo.setDestinationNode(destinationNode);
		movingInfo.setRoadLength(worldNodeInfo.getConnection()
				.get(destinationNode).getroadLength());
		movingInfo.setLeftRoadLength(worldNodeInfo.getConnection()
				.get(destinationNode).getroadLength());
		movingInfo.setRoadMonsterList(worldNodeInfo.getConnection()
				.get(destinationNode).getRoadMonster());
		movingInfo.setArrowName(worldNodeInfo.getConnection()
				.get(destinationNode).getArrowName());
	}
}
