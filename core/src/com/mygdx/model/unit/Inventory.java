package com.mygdx.model.unit;

import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
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
		if (rightHandGrip == null) {
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

}
