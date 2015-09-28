package com.mygdx.model.unit;

import java.util.ArrayList;

import com.mygdx.enums.MonsterEnum;
import com.mygdx.model.item.Item;

public class Monster extends Unit {
	public Monster() {
	}

	Monster(Status status) {
		this.status = status;
	}

	private ArrayList<Item> dropItems;
	private MonsterEnum.SizeType sizeType;
	private MonsterEnum.ElementType elementType;
	private int[][] hitArea;

	/* For Json Work */
	private ArrayList<String> dropItemList;

	public MonsterEnum.SizeType getSizeType() {
		return sizeType;
	}

	public void setSizeType(MonsterEnum.SizeType type) {
		this.sizeType = type;
	}

	public MonsterEnum.ElementType getElementType() {
		return elementType;
	}

	public void setElementType(MonsterEnum.ElementType elementType) {
		this.elementType = elementType;
	}

	public ArrayList<String> getDropItemList() {
		return dropItemList;
	}

	public void setDropItemList(ArrayList<String> dropItemList) {
		this.dropItemList = dropItemList;
	}

	public ArrayList<Item> getDropItems() {
		return dropItems;
	}

	public void setDropItems(ArrayList<Item> dropItems) {
		this.dropItems = dropItems;
	}

	public int[][] getHitArea() {
		return hitArea;
	}

	public void setHitArea(int[][] hitArea) {
		this.hitArea = hitArea;
	}

}
