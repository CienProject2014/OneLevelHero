package com.mygdx.model.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.mygdx.enums.BattleSituationEnum;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class BattleInfo {
	private BattleSituationEnum currentBattleSituation;
	private Skill currentSelectedSkill;
	private Item currentSelectedItem;
	private Monster currentMonster;
	private PriorityQueue<Unit> battleQueue;
	private List<Unit> stunnedUnitList;
	private String battleDescriptionLabel;
	private String backgroundPath;
	private String battleStateInfoLabel;
	private FieldTypeEnum fieldType;
	private Item dropItem;
	private int runPercent;

	public BattleInfo() {
		battleQueue = new PriorityQueue<>();
		stunnedUnitList = new ArrayList<>();
	}

	public Unit getCurrentActor() {
		return battleQueue.peek();
	}

	public Skill getCurrentSelectedSkill() {
		return currentSelectedSkill;
	}

	public void setCurrentSelectedSkill(Skill currentSelectedSkill) {
		this.currentSelectedSkill = currentSelectedSkill;
	}

	public Item getCurrentSelectedItem() {
		return currentSelectedItem;
	}

	public void setCurrentSelectedItem(Item currentSelectedItem) {
		this.currentSelectedItem = currentSelectedItem;
	}

	public Monster getCurrentMonster() {
		return currentMonster;
	}

	public void setCurrentMonster(Monster currentMonster) {
		this.currentMonster = currentMonster;
	}

	public PriorityQueue<Unit> getBattleQueue() {
		return battleQueue;
	}

	public void setBattleQueue(PriorityQueue<Unit> battleQueue) {
		this.battleQueue = battleQueue;
	}

	public BattleSituationEnum getCurrentBattleSituation() {
		return currentBattleSituation;
	}

	public void setCurrentBattleSituation(BattleSituationEnum currentBattleSituation) {
		this.currentBattleSituation = currentBattleSituation;
	}

	public List<Unit> getStunnedUnitList() {
		return stunnedUnitList;
	}

	public void setStunnedUnitList(List<Unit> stunnedUnitList) {
		this.stunnedUnitList = stunnedUnitList;
	}

	public String getBattleDescriptionLabel() {
		return battleDescriptionLabel;
	}

	public void setBattleDescriptionLabel(String battleDescriptionLabel) {
		this.battleDescriptionLabel = battleDescriptionLabel;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public String getBattleStateInfoLabel() {
		return battleStateInfoLabel;
	}

	public void setBattleStateInfoLabel(String battleStateInfoLabel) {
		this.battleStateInfoLabel = battleStateInfoLabel;
	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}

	public Item getDropItem() {
		return dropItem;
	}

	public void setDropItem(Item dropItem) {
		this.dropItem = dropItem;
	}

	public int getRunPercent() {
		return runPercent;
	}

	public void setRunPercent(int runPercent) {
		this.runPercent = runPercent;
	}
}
