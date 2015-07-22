package com.mygdx.model;

import com.mygdx.enums.ItemTypeEnum;

public class Item {
	private String name;
	private ItemTypeEnum itemType;
	private String grade;
	private String elementType;
	private Status addStatus;
	private int hitboxSize;
	private ItemEffect itemEffect;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemTypeEnum getItemType() {
		return itemType;
	}

	public void setItemType(ItemTypeEnum itemType) {
		this.itemType = itemType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public int getHitboxSize() {
		return hitboxSize;
	}

	public void setHitBoxSize(int hitboxSize) {
		this.hitboxSize = hitboxSize;
	}

	public ItemEffect getItemEffect() {
		return itemEffect;
	}

	public void setItemEffect(ItemEffect itemEffect) {
		this.itemEffect = itemEffect;
	}

	public Status getAddStatus() {
		return addStatus;
	}

	public void setAddStatus(Status addStatus) {
		this.addStatus = addStatus;
	}

}
