package com.mygdx.model.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.ItemEnum.HandgripState;
import com.mygdx.model.item.Equipment;

public class Inventory {
	private Equipment clothes;
	private Equipment accessory;
	private Equipment leftHandGrip;
	private Equipment rightHandGrip;

	public Float getAllDefense() {
		float allDefense;
		if (checkHasEquip(leftHandGrip) && checkHasEquip(rightHandGrip) && checkHasEquip(clothes)) {
			allDefense = clothes.getEffectStatus().getDefense() + getLeftHandGrip().getEffectStatus().getDefense()
					+ getRightHandGrip().getEffectStatus().getDefense();
			return allDefense;
		}
		return 0f;

	}

	private boolean checkHasEquip(Equipment equipment) {
		if (equipment == null)
			return false;
		return true;

	}

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

	public ItemEnum.HandgripState checkLeftHandGripState() {
		if (leftHandGrip.isEmpty()) {
			if (rightHandGrip.isEmpty()) {
				return HandgripState.ZERO_ZERO;
			} else {
				if (rightHandGrip.isTwoHanded()) {
					return HandgripState.ZERO_TWO;
				} else {
					return HandgripState.ZERO_ONE;
				}
			}
		} else {
			if (leftHandGrip.isTwoHanded()) {
				return HandgripState.TWO_ZERO;
			} else {
				if (rightHandGrip.isEmpty()) {
					return HandgripState.ONE_ZERO;
				} else {
					return HandgripState.ONE_ONE;
				}
			}
		}
	}

	public ItemEnum.HandgripState checkRightHandGripState() {
		if (leftHandGrip.isEmpty()) {
			if (rightHandGrip.isEmpty()) {
				return HandgripState.ZERO_ZERO;
			} else {
				if (rightHandGrip.isTwoHanded()) {
					return HandgripState.TWO_ZERO;
				} else {
					return HandgripState.ONE_ZERO;
				}
			}
		} else {
			if (leftHandGrip.isTwoHanded()) {
				return HandgripState.ZERO_TWO;
			} else {
				if (rightHandGrip.isEmpty()) {
					return HandgripState.ZERO_ONE;
				} else {
					return HandgripState.ONE_ONE;
				}
			}
		}
	}

	public Equipment getEquipment(ItemEnum equipmentPart) {
		switch (equipmentPart) {
		case RIGHT_HANDGRIP:
			return getRightHandGrip();
		case LEFT_HANDGRIP:
			return getLeftHandGrip();
		case ACCESSORY:
			return getAccessory();
		case CLOTHES:
			return getClothes();
		default:
			Gdx.app.log("Inventory", "잘못된 EquipmentPart정보");
			return null;
		}
	}

	public Array<String> getInventoryList() {
		Array<String> inventoryList = new Array<>();
		inventoryList.addAll("right_handgrip", "left_handgrip", "clothes", "accessory");
		return inventoryList;
	}
}
