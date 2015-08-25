package com.mygdx.enums;

public enum BuffEffectEnum {
	BLOCK_ACTION("block_action"), DECREASE_ATTACK("decrease_attack"), DECREASE_MAGIC_ATTACK("decrease_magic_attack");

	private String buffEffectEnum;

	private BuffEffectEnum(String enumString) {
		buffEffectEnum = enumString;
	}

	@Override
	public String toString() {
		return buffEffectEnum;
	}

	public static BuffEffectEnum findBuffEffectEnum(String stringName) {
		for (BuffEffectEnum buffEffectEnum : BuffEffectEnum.values())
			if (buffEffectEnum.toString().equals(stringName))
				return buffEffectEnum;
		return null;
	}
}
