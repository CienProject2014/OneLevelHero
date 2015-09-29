package com.mygdx.enums;

public enum BuffEffectEnum {
	BLOCK_ACTION("block_action"), DECREASE_ATTACK("decrease_attack"), DECREASE_MAGIC_ATTACK(
			"decrease_magic_attack"), DECREASE_HP_ITERATIVE("decrease_hp_iterative"), INCREASE_DEFENSE(
					"increase_defense"), DECREASE_DEFENSE("decrease_defense"), INCREASE_AGGRO(
							"increase_aggro"), OVERLOAD("overload"), OVERWORK("overwork"), DECREASE_SPEED(
									"decrease_speed"), STINK("stink"), FLY_ACTION("fly_action"), SHOCK("shock"), WEAK(
											"weak"), INCREASE_FIRE_RESISTANCE(
													"increase_fire_resistance"), INCREASE_WATER_RESISTANCE(
															"increase_water_resistance"), INCREASE_ELECTRIC_RESISTANCE(
																	"increase_electric_resistance"), DECLINE(
																			"decline"), CHARM("charm"), DEFAULT(
																					"default");

	private String buffEffect;

	BuffEffectEnum(String buffEffectString) {
		this.buffEffect = buffEffectString;
	}

	@Override
	public String toString() {
		return buffEffect;
	}

	public static BuffEffectEnum findBuffEffectEnum(String stringName) {
		for (BuffEffectEnum buffEffectEnum : BuffEffectEnum.values())
			if (buffEffectEnum.toString().equals(stringName))
				return buffEffectEnum;
		return null;
	}
}
