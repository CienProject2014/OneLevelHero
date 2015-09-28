package com.mygdx.enums;

public enum WorldNodeEnum {
	BLACKWOOD("blackwood"), BLACKWOOD_FOREST("blackwood_forest"), COBWEB(
			"cobweb");

	private String nodeName;

	WorldNodeEnum(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public String toString() {
		return nodeName;
	}

	public static WorldNodeEnum findWorldNodeEnum(String jsonName) {
		for (WorldNodeEnum worldNodeEnum : WorldNodeEnum.values())
			if (worldNodeEnum.toString().equals(jsonName))
				return worldNodeEnum;

		return null;
	}

	public enum NodeType {
		DUNGEON_ENTRANCE("dungeon_entrance"), VILLAGE("village"), FORK("fork");
		NodeType(String code) {
			this.code = code;
		}

		private String code;

		@Override
		public String toString() {
			return code;
		}
	}
}