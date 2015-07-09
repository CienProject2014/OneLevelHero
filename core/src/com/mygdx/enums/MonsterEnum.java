package com.mygdx.enums;

public enum MonsterEnum {
	ASSASSIN_GIRL("assassin girl");

	private String monsterName;

	MonsterEnum(String monsterName) {
		this.monsterName = monsterName;
	}

	@Override
	public String toString() {
		return monsterName;
	}

	public static MonsterEnum findMonsterEnum(String jsonName) {
		for (MonsterEnum monsterEnum : MonsterEnum.values())
			if (monsterEnum.toString().equals(jsonName))
				return monsterEnum;

		return null;
	}

	public enum SizeType {
		SMALL("small"), MEDIUM("medium"), LARGE("large");

		private String name;
		private float factor;

		SizeType(String typeName) {
			this.name = typeName;
		}

		@Override
		public String toString() {
			return name;
		}

		public static SizeType findTypeEnum(String jsonName) {
			for (SizeType typeEnum : SizeType.values())
				if (typeEnum.toString().equals(jsonName))
					return typeEnum;

			return null;
		}
	}

	public enum ElementType {
		FIRE("fire"), WATER("water"), EARTH("earth");

		private String name;

		ElementType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

		public static ElementType findTypeEnum(String jsonName) {
			for (ElementType type : ElementType.values())
				if (type.toString().equals(jsonName))
					return type;

			return null;
		}
	}
}
