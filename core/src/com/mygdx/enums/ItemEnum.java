package com.mygdx.enums;

public enum ItemEnum {
	WEAPON("weapon"), SHIELD("shield"), CLOTHES("clothes"), ACCESSORY(
			"accessory"), CONSUMABLES("consumables"), ETC_ITEM("etc_item");
	private String code;

	ItemEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public enum Inventory {
		EQUIPMENT, CONSUMABLES, ETC_ITEM;
	}

	public enum EquipmentPart {
		RIGHT_HAND_GRIP("right_hand_grip"), LEFT_HAND_GRIP("left_hand_grip"), CLOTHES(
				"clothes"), ACCESSORY("accessory");
		private String equipmentPartName;

		EquipmentPart(String equipmentPartName) {
			this.equipmentPartName = equipmentPartName;
		}

		@Override
		public String toString() {
			return equipmentPartName;
		}

		public static EquipmentPart findEquipmentPart(String stringName) {
			for (EquipmentPart equipmentPart : EquipmentPart.values())
				if (equipmentPart.toString().equals(stringName))
					return equipmentPart;
			return null;
		}
	}
}
