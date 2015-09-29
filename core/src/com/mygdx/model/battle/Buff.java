package com.mygdx.model.battle;

import com.mygdx.model.unit.Unit;

public class Buff {
	private String name;
	private String buffPath;
	private int duration;
	private int preFlyingTime;
	private int flyingTime;
	private int stackScope;
	private String buffType;
	private String[] buffEffectList;

	private Unit attacker;

	private int decreaseAttackPercent;
	private int decreaseMagicAttackPercent;

	private int decreaseHpPeriod;
	private int decreaseHpMagicPercent;
	private int decreaseHpAttackPercent;

	private int increaseFireResistance;
	private int increaseElectricResistance;
	private int increaseWaterResistance;

	private int decreaseHpOffset;

	private int increaseDefensePercent;

	public int getIncreaseDefensePercent() {
		return increaseDefensePercent;
	}

	public void setIncreaseDefensePercent(int increaseDefensePercent) {
		this.increaseDefensePercent = increaseDefensePercent;
	}

	public String getBuffPath() {
		return buffPath;
	}

	public void setBuffPath(String buffPath) {
		this.buffPath = buffPath;
	}

	public int getPreFlyingTime() {
		return preFlyingTime;
	}

	public void setPreFlyingTime(int preFlyingTime) {
		this.preFlyingTime = preFlyingTime;
	}

	public int getDecreaseHpMagicPercent() {
		return decreaseHpMagicPercent;
	}

	public void setDecreaseHpMagicPercent(int decreaseHpMagicPercent) {
		this.decreaseHpMagicPercent = decreaseHpMagicPercent;
	}

	public int getDecreaseHpAttackPercent() {
		return decreaseHpAttackPercent;
	}

	public void setDecreaseHpAttackPercent(int decreaseHpAttackPercent) {
		this.decreaseHpAttackPercent = decreaseHpAttackPercent;
	}

	public Unit getAttacker() {
		return attacker;
	}

	public void setAttacker(Unit attacker) {
		this.attacker = attacker;
	}

	public int getDecreaseAttackPercent() {
		return decreaseAttackPercent;
	}

	public void setDecreaseAttackPercent(int decreaseAttackPercent) {
		this.decreaseAttackPercent = decreaseAttackPercent;
	}

	public int getDecreaseMagicAttackPercent() {
		return decreaseMagicAttackPercent;
	}

	public void setDecreaseMagicAttackPercent(int decreaseMagicAttackPercent) {
		this.decreaseMagicAttackPercent = decreaseMagicAttackPercent;
	}

	public int getDecreaseHpPeriod() {
		return decreaseHpPeriod;
	}

	public void setDecreaseHpPeriod(int decreaseHpPeriod) {
		this.decreaseHpPeriod = decreaseHpPeriod;
	}

	public int getDecreaseHpOffset() {
		return decreaseHpOffset;
	}

	public void setDecreaseHpOffset(int decreaseHpOffset) {
		this.decreaseHpOffset = decreaseHpOffset;
	}

	public void addFlyingTime(int deltaTime) {
		flyingTime += deltaTime;
	}

	public int getFlyingTime() {
		return flyingTime;
	}

	public void setFlyingTime(int flyingTime) {
		this.flyingTime = flyingTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getStackScope() {
		return stackScope;
	}

	public void setStackScope(int stackScope) {
		this.stackScope = stackScope;
	}

	public String getBuffType() {
		return buffType;
	}

	public void setBuffType(String buffType) {
		this.buffType = buffType;
	}

	public String[] getBuffEffectList() {
		return buffEffectList;
	}

	public void setBuffEffectList(String[] buffEffectList) {
		this.buffEffectList = buffEffectList;
	}

	@Override
	public boolean equals(Object obj) {
		Buff buff = (Buff) obj;
		return this.name.equals(buff.getName());
	}

	public int getIncreaseFireResistance() {
		return increaseFireResistance;
	}

	public void setIncreaseFireResistance(int increaseFireResistance) {
		this.increaseFireResistance = increaseFireResistance;
	}

	public int getIncreaseElectricResistance() {
		return increaseElectricResistance;
	}

	public void setIncreaseElectricResistance(int increaseElectricResistance) {
		this.increaseElectricResistance = increaseElectricResistance;
	}

	public int getIncreaseWaterResistance() {
		return increaseWaterResistance;
	}

	public void setIncreaseWaterResistance(int increaseWaterResistance) {
		this.increaseWaterResistance = increaseWaterResistance;
	}

}
