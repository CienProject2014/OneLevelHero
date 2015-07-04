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

	public enum TypeEnum {
		SMALL("small"), MEDIUM("medium"), LARGE("large");

		private String typeName;
		private float factor;

		TypeEnum(String typeName) {
			this.typeName = typeName;
		}

		@Override
		public String toString() {
			return typeName;
		}

		public static TypeEnum findTypeEnum(String jsonName) {
			for (TypeEnum typeEnum : TypeEnum.values())
				if (typeEnum.toString().equals(jsonName))
					return typeEnum;

			return null;
		}
	}

}
