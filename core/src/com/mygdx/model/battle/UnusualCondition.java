package com.mygdx.model.battle;

import com.mygdx.enums.BuffTypeEnum;

public class UnusualCondition {
	private int zIndex;
	private String name;
	private String unusualConditionPath;
	private int duration;
	private int stackScope;
	private BuffTypeEnum buffType;
	private UnusualConditionEffect unusualConditionEffect;
	public int getzIndex() {
		return zIndex;
	}
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
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
	public BuffTypeEnum getBuffType() {
		return buffType;
	}
	public void setBuffType(BuffTypeEnum buffType) {
		this.buffType = buffType;
	}
	public UnusualConditionEffect getUnusualConditionEffect() {
		return unusualConditionEffect;
	}
	public void setUnusualConditionEffect(UnusualConditionEffect unusualConditionEffect) {
		this.unusualConditionEffect = unusualConditionEffect;
	}
	public String getUnusualConditionPath() {
		return unusualConditionPath;
	}
	public void setUnusualConditionPath(String unusualConditionPath) {
		this.unusualConditionPath = unusualConditionPath;
	}
}
