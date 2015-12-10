package com.mygdx.battle;

import com.mygdx.model.unit.Unit;

public interface BattleCommand {
	public void doCommand(Unit attackUnit, Unit defendUnit, int[][] hitArea);
}
