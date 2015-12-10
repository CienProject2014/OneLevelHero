package com.mygdx.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.battle.BattleCommand;
import com.mygdx.battle.DefendOnBattleCommand;
import com.mygdx.battle.GeneralAttackOnBattleCommand;
import com.mygdx.battle.NoCommandOnBattleCommand;
import com.mygdx.battle.RunAwayOnBattleCommand;
import com.mygdx.battle.UseItemOnBattleCommand;
import com.mygdx.battle.UseMagicOnBattleCommand;
import com.mygdx.battle.UseSkillOnBattleCommand;
import com.mygdx.battle.WaitOnBattleCommand;
import com.mygdx.enums.BattleCommandEnum;
import com.mygdx.model.unit.Unit;

public class BattleCommandController {
	@Autowired
	private WaitOnBattleCommand waitOnBattleCommand;
	@Autowired
	private DefendOnBattleCommand defendOnBattleCommand;
	@Autowired
	private NoCommandOnBattleCommand noCommandOnBattleCommand;
	@Autowired
	private RunAwayOnBattleCommand runAwayOnBattleCommand;
	@Autowired
	private UseItemOnBattleCommand useItemOnBattleCommand;
	@Autowired
	private UseMagicOnBattleCommand useMagicOnBattleCommand;
	@Autowired
	private UseSkillOnBattleCommand useSkillOnBattleCommand;
	@Autowired
	private GeneralAttackOnBattleCommand generalAttackOnBattleCommand;
	private BattleCommandEnum battleCommandEnum;
	private BattleCommand battleCommand;

	public void setBattleCommand(BattleCommandEnum battleCommandEnum) {
		this.setBattleCommandEnum(battleCommandEnum);
		switch (battleCommandEnum) {
			case DEFEND :
				battleCommand = defendOnBattleCommand;
				break;
			case GENERAL_ATTACK :
				battleCommand = generalAttackOnBattleCommand;
				break;
			case NO_COMMAND :
				battleCommand = noCommandOnBattleCommand;
				break;
			case RUN_AWAY :
				battleCommand = runAwayOnBattleCommand;
				break;
			case USE_ITEM :
				battleCommand = useItemOnBattleCommand;
				break;
			case USE_MAGIC :
				battleCommand = useMagicOnBattleCommand;
				break;
			case USE_SKILL :
				battleCommand = useSkillOnBattleCommand;
				break;
			case WAIT :
				battleCommand = waitOnBattleCommand;
				break;
			default :
				Gdx.app.log("BattleCommandController", "BattleCommand정보 오류");
				battleCommand = generalAttackOnBattleCommand;
				break;
		}
	}

	public void doBattleCommand(Unit attackUnit, Unit defendUnit, int[][] hitArea) {
		battleCommand.doCommand(attackUnit, defendUnit, hitArea);
	}

	public BattleCommand getBattleCommand() {
		return battleCommand;
	}

	public void setBattleCommand(BattleCommand battleCommand) {
		this.battleCommand = battleCommand;
	}

	public BattleCommandEnum getBattleCommandEnum() {
		return battleCommandEnum;
	}

	public void setBattleCommandEnum(BattleCommandEnum battleCommandEnum) {
		this.battleCommandEnum = battleCommandEnum;
	}

}
