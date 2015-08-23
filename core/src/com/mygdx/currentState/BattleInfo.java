package com.mygdx.currentState;

import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;

public class BattleInfo {
	private Monster currentMonster;
	private BattleStateEnum battleState;
	private CurrentClickStateEnum currentClickState;
	private Skill currentSelectedSkill;
	private Hero currentActor;
	private PositionEnum beforePosition;
	private FieldTypeEnum fieldType;
	private boolean isEventBattle;

	public Hero getCurrentActor() {
		return currentActor;
	}

	public void setCurrentActor(Hero currentActor) {
		this.currentActor = currentActor;
	}

	public BattleStateEnum getBattleState() {
		return battleState;
	}

	public void setBattleState(BattleStateEnum battleState) {
		this.battleState = battleState;
	}

	public CurrentClickStateEnum getcurrentClickStateEnum() {
		return currentClickState;
	}

	public void setCurrentClickStateEnum(CurrentClickStateEnum currentClickState) {
		this.currentClickState = currentClickState;
	}

	public Monster getCurrentMonster() {
		return currentMonster;
	}

	public void setCurrentMonster(Monster currentMonster) {
		this.currentMonster = currentMonster;
	}

	public PositionEnum getBeforePosition() {
		return beforePosition;
	}

	public void setBeforePosition(PositionEnum beforePosition) {
		this.beforePosition = beforePosition;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;

	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

	public boolean isEventBattle() {
		return isEventBattle;
	}

	public void setEventBattle(boolean isEventBattle) {
		this.isEventBattle = isEventBattle;
	}

	public Skill getCurrentSelectedSkill() {
		return currentSelectedSkill;
	}

	public void setCurrentSelectedSkill(Skill currentSelectedSkill) {
		this.currentSelectedSkill = currentSelectedSkill;
	}

}
