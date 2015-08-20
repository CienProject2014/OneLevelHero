package com.mygdx.model.battle;

import com.mygdx.enums.SkillEffectEnum;
import com.mygdx.enums.SkillTargetEnum;

public class Skill {
	private String name;
	private SkillEffectEnum skillEffectType;
	private SkillTargetEnum skillTargetType;
	private int skillFactor;
	private int magicFactor;
	private int duplicateNumber;
	private String[] effectNameList;
	private String oneRegex;

	private int costCasting;
	private int costGauge;
	private String elementType;
	private int hitboxSize;
	private int[] hitboxCenter;
	private int[][] hitboxShape;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillEffectEnum getSkillEffectType() {
		return skillEffectType;
	}

	public void setSkillEffectType(SkillEffectEnum skillEffectType) {
		this.skillEffectType = skillEffectType;
	}

	public SkillTargetEnum getSkillTargetType() {
		return skillTargetType;
	}

	public void setSkillTargetType(SkillTargetEnum skillTargetType) {
		this.skillTargetType = skillTargetType;
	}

	public int getSkillFactor() {
		return skillFactor;
	}

	public void setSkillFactor(int skillFactor) {
		this.skillFactor = skillFactor;
	}

	public int getMagicFactor() {
		return magicFactor;
	}

	public void setMagicFactor(int magicFactor) {
		this.magicFactor = magicFactor;
	}

	public int getDuplicateNumber() {
		return duplicateNumber;
	}

	public void setDuplicateNumber(int duplicateNumber) {
		this.duplicateNumber = duplicateNumber;
	}

	public String[] getEffectNameList() {
		return effectNameList;
	}

	public void setEffectNameList(String[] effectNameList) {
		this.effectNameList = effectNameList;
	}

	public String getOneRegex() {
		return oneRegex;
	}

	public void setOneRegex(String oneRegex) {
		this.oneRegex = oneRegex;
	}

	public int getCostCasting() {
		return costCasting;
	}

	public void setCostCasting(int costCasting) {
		this.costCasting = costCasting;
	}

	public int getCostGauge() {
		return costGauge;
	}

	public void setCostGauge(int costGauge) {
		this.costGauge = costGauge;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public int getHitboxSize() {
		return hitboxSize;
	}

	public void setHitboxSize(int hitboxSize) {
		this.hitboxSize = hitboxSize;
	}

	public int[] getHitboxCenter() {
		return hitboxCenter;
	}

	public void setHitboxCenter(int[] hitboxCenter) {
		this.hitboxCenter = hitboxCenter;
	}

	public int[][] getHitboxShape() {
		return hitboxShape;
	}

	public void setHitboxShape(int[][] hitboxShape) {
		this.hitboxShape = hitboxShape;
	}

}
