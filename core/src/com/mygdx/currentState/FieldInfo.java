package com.mygdx.currentState;

import java.util.List;

import com.mygdx.enums.FieldTypeEnum;

public class FieldInfo {
	private int fieldNumber;
	private String startNode, destinationNode, arrowName;
	private List<FieldTypeEnum> fieldList;
	private boolean inField;

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
}
