package com.mygdx.battle;

import com.mygdx.model.unit.Unit;

public class WaitOnBattleCommand implements BattleCommand {
	@Override
	public void doCommand(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		attackUnit.setSubValue(1);
	}
}
