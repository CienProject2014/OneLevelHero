package com.mygdx.enums;

public enum NpcEnum {
	MAWANG("mawang"), WAIJI("waiji");
	private String npcName;

	private NpcEnum(String npcName) {
		this.npcName = npcName;
	}

	@Override
	public String toString() {
		return npcName;
	}
}
