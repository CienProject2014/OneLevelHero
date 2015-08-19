package com.mygdx.model.battle;

import com.mygdx.enums.SkillEffectEnum;
import com.mygdx.enums.SkillTargetEnum;
import com.mygdx.enums.SkillTypeEnum;

public class Skill {
	private String name;
	private SkillTypeEnum skillType;
	private int costCasting;
	private int costGauge;
	private String elementType;
	private int hitboxSize;
	private int[] hitboxCenter;
	private int[][] hitboxShape;
	private int damage;
	private SkillTargetEnum skillTargetType;
	private SkillEffectEnum skillEffectType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillTypeEnum getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillTypeEnum skillType) {
		this.skillType = skillType;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public SkillTargetEnum getSkillTargetType() {
		return skillTargetType;
	}

	public void setSkillTargetType(SkillTargetEnum skillTargetType) {
		this.skillTargetType = skillTargetType;
	}

	public SkillEffectEnum getSkillEffectType() {
		return skillEffectType;
	}

	public void setSkillEffectType(SkillEffectEnum skillEffectType) {
		this.skillEffectType = skillEffectType;
	}

}
