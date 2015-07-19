package com.mygdx.model;

import com.mygdx.enums.SkillTypeEnum;

public class Skill {
	private String name;
	private String target;
	private SkillTypeEnum skillType;
	private int costCasting;
	private int costGauge;
	private String elementType;
	private int hitboxSize;
	private int damage;
	private SkillEffect skillEffect;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public SkillEffect getSkillEffect() {
		return skillEffect;
	}

	public void setSkillEffect(SkillEffect skillEffect) {
		this.skillEffect = skillEffect;
	}
}
