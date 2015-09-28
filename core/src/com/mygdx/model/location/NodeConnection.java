package com.mygdx.model.location;

import java.util.ArrayList;

import com.mygdx.enums.FieldTypeEnum;

public class NodeConnection {
	private ArrayList<FieldTypeEnum> fieldList;
	private String arrowName;

	public String getArrowName() {
		return arrowName;
	}

	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}

	public ArrayList<FieldTypeEnum> getFieldList() {
		return fieldList;
	}

	public void setFieldList(ArrayList<FieldTypeEnum> fieldList) {
		this.fieldList = fieldList;
	}
}
