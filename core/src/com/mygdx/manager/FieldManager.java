package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.model.surroundings.NodeConnection;
import com.mygdx.model.surroundings.WorldNode;
import com.mygdx.store.Loadable;
import com.mygdx.store.Savable;

public class FieldManager implements Savable<FieldInfo>, Loadable<FieldInfo> {
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private EncounterManager encounterManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;

	private FieldInfo fieldInfo = new FieldInfo();

	public FieldTypeEnum getFieldType() {
		return fieldInfo.getFieldList().get(getFieldNumber());
	}

	public String getDestinationNode() {
		return fieldInfo.getDestinationNode();
	}

	public int getLeftFieldLength() {
		return getFieldLength() - getFieldNumber();
	}

	public int getFieldNumber() {
		return fieldInfo.getFieldNumber();
	}

	public void startMovingField(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets
				.getWorldNodeInfo(positionManager.getCurrentNodeName());

		String startNode = positionManager.getCurrentNodeName();
		NodeConnection conn = worldNodeInfo.getNodeConnection().get(
				destinationNode);

		fieldInfo = new FieldInfo(startNode, destinationNode, conn);
	}

	public boolean isInField() {
		return fieldInfo.isInField();
	}

	public int getFieldLength() {
		return fieldInfo.getFieldList().size();
	}

	public FieldInfo goFowardAndGetFieldInfo() {
		if (!fieldInfo.tryToGoForward()) {
			encounterManager.act();
		} else {
			positionManager.setCurrentNodeName(fieldInfo.getDestinationNode());
			movingManager.goCurrentPosition();
		}
		return fieldInfo;
	}

	public FieldInfo goBackwardAndGetFieldInfo() {
		if (!fieldInfo.tryToGoBackword()) {
			encounterManager.act();
		} else {
			positionManager.setCurrentNodeName(fieldInfo.getDestinationNode());
			movingManager.goCurrentPosition();
		}
		return fieldInfo;
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

	public String getArrowName() {
		return fieldInfo.getArrowName();
	}

	@Override
	public void setData(FieldInfo fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

	@Override
	public FieldInfo getData() {
		return fieldInfo;
	}

}
