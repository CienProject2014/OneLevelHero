package com.mygdx.model.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.enums.ItemEnum;
import com.mygdx.model.item.Equipment;

public class Inventory {
	private Equipment clothes;
	private Equipment accessory;
	private Equipment leftHandGrip;
	private Equipment rightHandGrip;

	public Equipment getClothes() {
		return clothes;
	}

	public void setClothes(Equipment clothes) {
		this.clothes = clothes;
	}

	public Equipment getAccessory() {
		return accessory;
	}

	public void setAccessory(Equipment accessory) {
		this.accessory = accessory;
	}

	public Equipment getLeftHandGrip() {
		return leftHandGrip;
	}

	public void setLeftHandGrip(Equipment leftHandGrip) {
		this.leftHandGrip = leftHandGrip;
	}

	public Equipment getRightHandGrip() {
		return rightHandGrip;
	}

	public void setRightHandGrip(Equipment rightHandGrip) {
		this.rightHandGrip = rightHandGrip;
	}

	public boolean isLeftHandGripUsable(Equipment usingLeftHandGrip) {
		if (usingLeftHandGrip.isEmpty()) {
			return true;
		} else {
			if (usingLeftHandGrip.isTwoHanded()) {
				return false;
			} else {
				if (!usingLeftHandGrip.isTwoHanded()) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public Equipment getEquipment(ItemEnum equipmentPart) {
		switch (equipmentPart) {
			case RIGHT_HANDGRIP :
				return getRightHandGrip();
			case LEFT_HANDGRIP :
				return getLeftHandGrip();
			case ACCESSORY :
				return getAccessory();
			case CLOTHES :
				return getClothes();
			default :
				Gdx.app.log("Inventory", "잘못된 EquipmentPart정보");
				return null;
		}
	}

	public boolean isRightHandGripUsable(Equipment usingLeftHandGrip) {
		if (usingLeftHandGrip.isEmpty()) {
			return true;
		} else {
			if (usingLeftHandGrip.isTwoHanded()) {
				return false;
			} else {
				if (!usingLeftHandGrip.isTwoHanded()) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public Array<String> getInventoryList() {
		Array<String> inventoryList = new Array<>();
		inventoryList.addAll("right_handgrip", "left_handgrip", "clothes", "accessory");
		return inventoryList;
	}
}
