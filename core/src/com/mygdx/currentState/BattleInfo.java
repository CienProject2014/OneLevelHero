package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.Queue;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class BattleInfo {
	private Monster currentMonster;
	private BattleStateEnum battleState;
	private CurrentClickStateEnum currentClickState;
	private Skill currentSelectedSkill;
	private Hero currentActor;
	private PositionEnum beforePosition;
	private FieldTypeEnum fieldType;
	private ArrayList<Unit> units;
	private Queue<Unit> orderedUnits;
	private ArrayList<ImageButton> rMenuButtonList;
	private boolean isEventBattle;
	private boolean isSmallUpdate;
	private boolean isBigUpdate;
	private boolean showGrid;
	private Unit currentAttackUnit;
	private int runPercent;
	private boolean isSkill;

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

	public boolean isShowGrid() {
		return showGrid;
	}

	public void setShowGrid(boolean showGrid) {
		this.showGrid = showGrid;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public Queue<Unit> getOrderedUnits() {
		return orderedUnits;
	}

	public void setOrderedUnits(Queue<Unit> orderedUnits) {
		this.orderedUnits = orderedUnits;
	}

	public ArrayList<ImageButton> getrMenuButtonList() {
		return rMenuButtonList;
	}

	public void setrMenuButtonList(ArrayList<ImageButton> rMenuButtonList) {
		this.rMenuButtonList = rMenuButtonList;
	}

	public boolean isSmallUpdate() {
		return isSmallUpdate;
	}

	public void setSmallUpdate(boolean isUpdate) {
		this.isSmallUpdate = isUpdate;
	}

	public int getRunPercent() {
		return runPercent;
	}

	public void setRunPercent(int runPercent) {
		this.runPercent = runPercent;
	}

	public Unit getCurrentAttackUnit() {
		return currentAttackUnit;
	}

	public void setCurrentAttackUnit(Unit currentAttackUnit) {
		this.currentAttackUnit = currentAttackUnit;
	}

	public boolean isBigUpdate() {
		return isBigUpdate;
	}

	public void setBigUpdate(boolean isBigUpdate) {
		this.isBigUpdate = isBigUpdate;
	}

	public boolean isSkill() {
		return isSkill;
	}

	public void setSkill(boolean isSkill) {
		this.isSkill = isSkill;
	}

}
