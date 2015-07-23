package com.mygdx.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.WorldNode;

public class MovingManager {
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private EncounterManager encounterManager;

	public List<String> getRoadMonsters() {
		return movingInfo.getRoadMonsterList();
	}

	public void selectDestinationNode(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets
				.getWorldNodeInfo(positionManager.getCurrentNode());
		createMovingInfo(destinationNode, worldNodeInfo);
	}

	public void createMovingInfo(String destinationNode, WorldNode worldNodeInfo) {
		movingInfo.setStartNode(positionManager.getCurrentNode());
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

	public void goForward() {
		if (isRoadLeft()) {
			minusLeftRoadLength();
			movingRoad();
		} else {
			// 목적지 노드에 도착해서 현재 위치로 설정함
			positionManager.setCurrentNode(movingInfo.getDestinationNode());
			goIntoCurrentNode();
		}
	}

	public void goBackward() {
		if (!isRoadFull()) {
			plusLeftRoadLength();
			movingRoad();
		} else {
			// 원래 노드로 다시 돌아옴
			positionManager.setCurrentNode(movingInfo.getStartNode());
			goIntoCurrentNode();
		}
	}

	private void plusLeftRoadLength() {
		movingInfo.setLeftRoadLength(movingInfo.getLeftRoadLength() + 1);
	}

	public void minusLeftRoadLength() {
		movingInfo.setLeftRoadLength(movingInfo.getLeftRoadLength() - 1);
	}

	private void movingRoad() {
		if (encounterManager.isBattleOccured()) {
			encounterManager.encountEnemy();
		}
	}

	private void goIntoCurrentNode() {
		positionManager.setCurrentPlace(positionManager.getCurrentNodeType());
		positionManager.goCurrentPlace();
	}

	private boolean isRoadLeft() {
		return (movingInfo.getLeftRoadLength() > 0) ? true : false;
	}

	private boolean isRoadFull() {
		return (movingInfo.getRoadLength() <= movingInfo.getLeftRoadLength()) ? true
				: false;
	}

	public String getDestinationNode() {
		return movingInfo.getDestinationNode();
	}

	public String getLeftRoadLength() {
		return String.valueOf(movingInfo.getLeftRoadLength());
	}

}
