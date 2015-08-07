package com.mygdx.model.item;

import com.mygdx.model.unit.Status;

public class Equipment extends Item {
	private Status effectStatus;

	public Status getEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(Status effectStatus) {
		this.effectStatus = effectStatus;
	}
}
