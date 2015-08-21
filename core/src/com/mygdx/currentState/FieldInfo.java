package com.mygdx.currentState;

import java.util.List;

import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.model.surroundings.NodeConnection;

public class FieldInfo {
	private int fieldNumber;
	private String startNode, destinationNode, arrowName;
	private List<FieldTypeEnum> fieldList;
	private boolean inField;

	public void setFieldInfo(String startNode, String destinationNode, NodeConnection conn) {
		this.startNode = startNode;
		this.destinationNode = destinationNode;
		this.fieldList = conn.getFieldList();
		this.arrowName = conn.getArrowName();

		this.inField = true;
		this.fieldNumber = 0;
	}

	public String getArrowName() {
		return arrowName;
	}

	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}

	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	public String getDestinationNode() {
		return destinationNode;
	}

	public void setDestinationNode(String destinationNode) {
		this.destinationNode = destinationNode;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public boolean isInField() {
		return inField;
	}

	public void setInField(boolean inField) {
		this.inField = inField;
	}

	public List<FieldTypeEnum> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldTypeEnum> fieldList) {
		this.fieldList = fieldList;
	}

	public FieldTypeEnum getCurrentFieldType() {
		return fieldList.get(fieldNumber);
	}

	// FIXME need to check index out of bound!
	private void increaseFieldNumber() {
		this.fieldNumber += 1;
	}

	private void decreaseFieldNumber() {
		this.fieldNumber -= 1;
	}

	// syntax sugar

	private boolean willBeReturn() {
		return fieldNumber == 0 ? true : false;
	}

	private boolean willBeArrived() {
		return fieldNumber >= fieldList.size() - 1 ? true : false;
	}

	public boolean tryToGoForward() {
		boolean willBeArrvied = willBeArrived();
		if (!willBeArrvied) {
			increaseFieldNumber();
		} else {
			inField = false;
		}
		return willBeArrvied;
	}

	public boolean tryToGoBackword() {
		boolean willbeReturn = willBeReturn();
		if (!willbeReturn) {
			decreaseFieldNumber();
		} else {
			inField = false;
		}
		return willbeReturn;
	}
}
