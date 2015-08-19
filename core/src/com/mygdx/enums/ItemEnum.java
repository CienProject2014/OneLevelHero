package com.mygdx.enums;

public enum ItemEnum {
	RIGHT_HANDGRIP("right_handgrip"), LEFT_HANDGRIP("left_handgrip"), HANDGRIP("handgrip"), CLOTHES("clothes"), ACCESSORY(
			"accessory"), CONSUMABLES("consumables"), ETC_ITEM("etc_item");
	private String code;

	ItemEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
	public static ItemEnum findItemByType(String stringName) {
		for (ItemEnum itemType : ItemEnum.values()) {
			if (itemType.toString().equals(stringName)) {
				return itemType;
			}
		}
		return null;
	}

	public enum Inventory {
		EQUIPMENT, CONSUMABLES, ETC_ITEM;
	}
}
