package com.mygdx.model.battle;

public class Buff {
	private String name;
	private String buffType;
	private int stackScope;
	private int duration;
	private String buffEffectEnum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuffType() {
		return buffType;
	}

	public void setBuffType(String buffType) {
		this.buffType = buffType;
	}

	public int getStackScope() {
		return stackScope;
	}

	public void setStackScope(int stackScope) {
		this.stackScope = stackScope;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getBuffEffectEnum() {
		return buffEffectEnum;
	}

	public void setBuffEffectEnum(String buffEffectEnum) {
		this.buffEffectEnum = buffEffectEnum;
	}

}
