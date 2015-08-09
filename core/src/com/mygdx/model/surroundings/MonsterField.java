package com.mygdx.model.surroundings;

import java.util.ArrayList;

import com.mygdx.enums.FieldTypeEnum;

public class MonsterField {
	private FieldTypeEnum fieldType;
	private ArrayList<String> fieldMonsterList;

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}
	public ArrayList<String> getFieldMonsterList() {
		return fieldMonsterList;
	}
	public void setFieldMonsterList(ArrayList<String> fieldMonsterList) {
		this.fieldMonsterList = fieldMonsterList;
	}
}
