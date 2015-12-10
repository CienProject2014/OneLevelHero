package com.mygdx.battle;

public class Skill {
	private String skillPath;
	private String name;
	private String skillType;
	private String skillEffectType;
	private String skillTargetType;
	private int skillFactor;
	private int magicFactor;
	private int duplicateNumber;
	private String[] effectNameList;
	private String buffName;
	private String[] buffNameList;
	private String oneRegex;

	private int costCasting;
	private int costGauge;
	private String elementType;
	private int hitboxSize;
	private int[] hitboxCenter;
	private int[][] hitboxShape;
	private String description;
	private String skillCheckType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getSkillEffectType() {
		return skillEffectType;
	}

	public void setSkillEffectType(String skillEffectType) {
		this.skillEffectType = skillEffectType;
	}

	public void setSkillCheckType(String skillEffectType) {
		this.skillCheckType = skillEffectType;
	}

	public String getSkillCheckType() {
		return skillCheckType;
	}

	public String getSkillTargetType() {
		return skillTargetType;
	}

	public void setSkillTargetType(String skillTargetType) {
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

	public String getSkillPath() {
		return skillPath;
	}

	public void setSkillPath(String skillPath) {
		this.skillPath = skillPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuffName() {
		return buffName;
	}

	public void setBuffName(String buffName) {
		this.buffName = buffName;
	}

	public String[] getBuffNameList() {
		return buffNameList;
	}

	public void setBuffNameList(String[] buffNameList) {
		this.buffNameList = buffNameList;
	}

}
