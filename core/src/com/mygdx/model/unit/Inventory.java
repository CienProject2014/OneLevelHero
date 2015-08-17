package com.mygdx.model.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.enums.ItemEnum;
import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.item.HandGrip;

public class Inventory {
	private Clothes clothes;
	private Accessory accessory;
	private HandGrip leftHandGrip;
	private HandGrip rightHandGrip;

	public Clothes getClothes() {
		return clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	public Accessory getAccessory() {
		return accessory;
	}

	public void setAccessory(Accessory accessory) {
		this.accessory = accessory;
	}

	public HandGrip getLeftHandGrip() {
		return leftHandGrip;
	}

	public void setLeftHandGrip(HandGrip leftHandGrip) {
		this.leftHandGrip = leftHandGrip;
	}

	public HandGrip getRightHandGrip() {
		return rightHandGrip;
	}

	public void setRightHandGrip(HandGrip rightHandGrip) {
		this.rightHandGrip = rightHandGrip;
	}

	public boolean isLeftHandGripUsable(HandGrip usingLeftHandGrip) {
		if (rightHandGrip.isEmpty()) {
			return true;
		} else {
			if (rightHandGrip.isTwoHanded()) {
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

	public Equipment getEquipment(ItemEnum.EquipmentPart equipmentPart) {
		switch (equipmentPart) {
			case RIGHT_HAND_GRIP :
				return getRightHandGrip();
			case LEFT_HAND_GRIP :
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

	public boolean isRightHandGripUsable(HandGrip usingLeftHandGrip) {
		if (usingLeftHandGrip == null) {
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
		inventoryList.addAll("right_hand_grip", "left_hand_grip", "clothes",
				"accessory");
		return inventoryList;
	}
}
