package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.enums.FieldTypeEnum;
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

	public FieldTypeEnum getFieldType() {
		return fieldInfo.getFieldList().get(getFieldNumber());
	}

	public String getDestinationNode() {
		return fieldInfo.getDestinationNode();
	}

	public String getLeftFieldLength() {
		return String.valueOf(getFieldLength() - getFieldNumber());
	}

	public int getFieldNumber() {
		return fieldInfo.getFieldNumber();
	}

	public void startMovingField(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets
				.getWorldNodeInfo(positionManager.getCurrentNodeName());
		createFieldInfo(destinationNode, worldNodeInfo);
	}

	public boolean isInField() {
		return fieldInfo.isInField();
	}

	public int getFieldLength() {
		return fieldInfo.getFieldList().size();
	}

	public void createFieldInfo(String destinationNode, WorldNode worldNodeInfo) {
		fieldInfo.setStartNode(positionManager.getCurrentNodeName());
		fieldInfo.setDestinationNode(destinationNode);
		fieldInfo.setFieldNumber(0);
		fieldInfo.setFieldList(worldNodeInfo.getNodeConnection()
				.get(destinationNode).getFieldList());
		fieldInfo.setArrowName(worldNodeInfo.getNodeConnection()
				.get(destinationNode).getArrowName());
		fieldInfo.setInField(true);
	}

	public void goForwardField() {
		if (!willBeArrived()) {
			increaseFieldNumber();
			moveField();
		} else {
			positionManager.setCurrentNodeName(fieldInfo.getDestinationNode());
			fieldInfo.setInField(false);
			movingManager.goCurrentPosition();
		}
	}

	public void goBackwardField() {
		if (!willComeBack()) {
			decreaseFieldNumber();
			moveField();
		} else {
			positionManager.setCurrentNodeName(fieldInfo.getStartNode());
			fieldInfo.setInField(false);
			movingManager.goCurrentPosition();
		}
	}

	private void setFieldNumber(int fieldNumber) {
		fieldInfo.setFieldNumber(fieldNumber);
	}

	private void decreaseFieldNumber() {
		setFieldNumber(getFieldNumber() - 1);
	}

	private void increaseFieldNumber() {
		setFieldNumber(fieldInfo.getFieldNumber() + 1);
	}

	private void moveField() {
		if (encounterManager.isBattleOccured()) {
			encounterManager.encountEnemy();
		}
	}

	private boolean willBeArrived() {
		return (getFieldNumber() >= getFieldLength() - 1) ? true : false;
	}

	private boolean willComeBack() {
		return (getFieldNumber() == 0) ? true : false;
	}
}
