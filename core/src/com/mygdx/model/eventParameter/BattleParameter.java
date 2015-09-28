package com.mygdx.model.eventParameter;

public class BattleParameter extends Parameter {
	private String targetMonster;
	private String backgroundPath;
	private String battleCommand;

	public String getBackgroundPath() {
		return backgroundPath;
	}
	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}
	public String getTargetMonster() {
		return targetMonster;
	}
	public void setTargetMonster(String targetMonster) {
		this.targetMonster = targetMonster;
	}
	public String getBattleCommand() {
		return battleCommand;
	}
	public void setBattleCommand(String battleCommand) {
		this.battleCommand = battleCommand;
	}

}
