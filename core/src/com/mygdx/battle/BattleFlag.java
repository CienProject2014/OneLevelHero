package com.mygdx.battle;

public class BattleFlag {
	private boolean isGetItem;
	private boolean isEndBuff;
	private boolean isEventBattle;
	private boolean isSmallUpdate;
	private boolean isBigUpdate;
	private boolean isUsingSkill;
	private boolean isShowGrid;
	private boolean isMonsterTurnEnd;
	public boolean isGetItem() {
		return isGetItem;
	}
	public void setGetItem(boolean isGetItem) {
		this.isGetItem = isGetItem;
	}
	public boolean isEndBuff() {
		return isEndBuff;
	}
	public void setEndBuff(boolean isEndBuff) {
		this.isEndBuff = isEndBuff;
	}
	public boolean isEventBattle() {
		return isEventBattle;
	}
	public void setEventBattle(boolean isEventBattle) {
		this.isEventBattle = isEventBattle;
	}
	public boolean isSmallUpdate() {
		return isSmallUpdate;
	}
	public void setSmallUpdate(boolean isSmallUpdate) {
		this.isSmallUpdate = isSmallUpdate;
	}
	public boolean isBigUpdate() {
		return isBigUpdate;
	}
	public void setBigUpdate(boolean isBigUpdate) {
		this.isBigUpdate = isBigUpdate;
	}
	public boolean isUsingSkill() {
		return isUsingSkill;
	}
	public void setUsingSkill(boolean isSkill) {
		this.isUsingSkill = isSkill;
	}
	public boolean isShowGrid() {
		return isShowGrid;
	}
	public void setShowGrid(boolean isShowGrid) {
		this.isShowGrid = isShowGrid;
	}
	public boolean isMonsterTurnEnd() {
		return isMonsterTurnEnd;
	}
	public void setMonsterTurnEnd(boolean isMonsterTurnEnd) {
		this.isMonsterTurnEnd = isMonsterTurnEnd;
	}

}
