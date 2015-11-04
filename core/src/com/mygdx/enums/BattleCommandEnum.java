package com.mygdx.enums;

public enum BattleCommandEnum {
	NO_COMMAND("no_command", 0), GENERAL_ATTACK("general_attack", 30), USE_MAGIC("use_magic", 0), USE_SKILL(
			"use_skill", 0), USE_ITEM("use_item", 30), DEFEND("defend", 20), RUN_AWAY("run_away", 0), WAIT("wait", 0);

	private String code;
	private int costGauge;

	BattleCommandEnum(String code, int costCauge) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}

	public int getCostGauge() {
		return costGauge;
	}
}
