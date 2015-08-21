package com.mygdx.model.unit;

import java.util.HashMap;

import com.mygdx.enums.ItemEnum;
import com.mygdx.unitStrategy.InventoryStrategy;

public class Hero extends Unit {
	private Inventory inventory;
	private InventoryStrategy inventoryStrategy;

	/* For Json Work */
	private HashMap<String, String> initialInventoryList;

	public InventoryStrategy getInventoryStrategy() {
		return inventoryStrategy;
	}

	public void setInventoryStrategy(InventoryStrategy inventoryStrategy) {
		this.inventoryStrategy = inventoryStrategy;
	}
	public void equip(ItemEnum equipmentType, String equipmentName) {
		inventoryStrategy.equip(this, equipmentType, equipmentName);
	}

	public void equipClothes(String clothesName) {
		inventoryStrategy.equipClothes(this, clothesName);
	}

	public void unEquipClothes() {
		inventoryStrategy.unEquipClothes(this);
	}

	public void equipRightHandGrip(String rightHandGripName) {
		inventoryStrategy.equipRightHandGrip(this, rightHandGripName);
	}

	public void equipLeftHandGrip(String leftHandGripName) {
		inventoryStrategy.equipLeftHandGrip(this, leftHandGripName);
	}

	public void unEquipRightHandGrip() {
		inventoryStrategy.unEquipRightHandGrip(this);
	}

	public void unEquipLeftHandGrip() {
		inventoryStrategy.unEquipLeftHandGrip(this);
	}

	public void equipAccessory(String accessoryName) {
		inventoryStrategy.equipAccessory(this, accessoryName);
	}

	public void unEquipAccessory() {
		inventoryStrategy.unEquipAccessory(this);
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public HashMap<String, String> getInitialInventoryList() {
		return initialInventoryList;
	}

	public void setInitialInventoryList(HashMap<String, String> initialInventoryList) {
		this.initialInventoryList = initialInventoryList;
	}
}