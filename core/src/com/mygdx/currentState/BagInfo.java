package com.mygdx.currentState;

import java.util.ArrayList;

import com.mygdx.model.item.Consumables;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.item.Item;

public class BagInfo {
	private ArrayList<Equipment> equipmentList = new ArrayList<>();
	private ArrayList<Consumables> consumablesList = new ArrayList<>();
	private ArrayList<Item> etcItemList = new ArrayList<>();

	public ArrayList<Equipment> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(ArrayList<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public ArrayList<Consumables> getConsumablesList() {
		return consumablesList;
	}
	public void setConsumablesList(ArrayList<Consumables> consumablesList) {
		this.consumablesList = consumablesList;
	}
	public ArrayList<Item> getEtcItemList() {
		return etcItemList;
	}
	public void setEtcItemList(ArrayList<Item> etcItemList) {
		this.etcItemList = etcItemList;
	}
}
