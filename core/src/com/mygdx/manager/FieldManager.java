package com.mygdx.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.model.surroundings.WorldNode;

public class FieldManager {
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private FieldInfo fieldInfo;
	@Autowired
	private EncounterManager encounterManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;

	public List<String> getRoadMonsters() {
		return fieldInfo.getRoadMonsterList();
	}

	public void selectDestinationNode(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets
				.getWorldNodeInfo(positionManager.getCurrentNodeName());
		createMovingInfo(destinationNode, worldNodeInfo);
		fieldInfo.setInRoad(true);
	}

	public boolean isInRoad() {
		return fieldInfo.isInRoad();
	}

	public void createMovingInfo(String destinationNode, WorldNode worldNodeInfo) {
		fieldInfo.setStartNode(positionManager.getCurrentNodeName());
		fieldInfo.setDestinationNode(destinationNode);
		fieldInfo.setRoadLength(worldNodeInfo.getConnection()
				.get(destinationNode).getroadLength());
		fieldInfo.setLeftRoadLength(worldNodeInfo.getConnection()
				.get(destinationNode).getroadLength());
		fieldInfo.setRoadMonsterList(worldNodeInfo.getConnection()
				.get(destinationNode).getRoadMonster());
		fieldInfo.setArrowName(worldNodeInfo.getConnection()
				.get(destinationNode).getArrowName());
	}

	public void goForwardField() {
		if (isRoadLeft()) {
			minusLeftRoadLength();
			movingRoad();
		} else {
			// 목적지 노드에 도착해서 현재 위치로 설정함
			positionManager.setCurrentNodeName(fieldInfo.getDestinationNode());
			fieldInfo.setInRoad(false);
			movingManager.goCurrentPosition();
		}
	}

	public void goBackwardField() {
		if (!isRoadFull()) {
			plusLeftRoadLength();
			movingRoad();
		} else {
			// 원래 노드로 다시 돌아옴
			positionManager.setCurrentNodeName(fieldInfo.getStartNode());
			fieldInfo.setInRoad(false);
			movingManager.goCurrentPosition();
		}
	}

	private void plusLeftRoadLength() {
		fieldInfo.setLeftRoadLength(fieldInfo.getLeftRoadLength() + 1);
	}

	public void minusLeftRoadLength() {
		fieldInfo.setLeftRoadLength(fieldInfo.getLeftRoadLength() - 1);
	}

	private void movingRoad() {
		if (encounterManager.isBattleOccured()) {
			encounterManager.encountEnemy();
		}
	}

	private boolean isRoadLeft() {
		return (fieldInfo.getLeftRoadLength() > 0) ? true : false;
	}

	private boolean isRoadFull() {
		return (fieldInfo.getRoadLength() <= fieldInfo.getLeftRoadLength()) ? true
				: false;
	}

	public String getDestinationNode() {
		return fieldInfo.getDestinationNode();
	}

	public String getLeftRoadLength() {
		return String.valueOf(fieldInfo.getLeftRoadLength());
	}

}
