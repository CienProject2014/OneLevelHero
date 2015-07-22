package com.mygdx.model;

import java.util.ArrayList;
import java.util.Map;

public class Hero extends Unit {
	private Equipment equipment;
	private Map<String, Item> items;

	/* For Json Work */
	private ArrayList<String> itemList;

	public ArrayList<String> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<String> itemList) {
		this.itemList = itemList;
	}

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
}