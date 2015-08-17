package com.mygdx.model.item;

import com.mygdx.model.unit.Status;

public class Equipment extends Item {
	public final String EMPTY_ITEM = "empty_item";
	private boolean using;
	private Status effectStatus;
	private String usingHand;
	private boolean isTwoHanded;

	public String getUsingHand() {
		return usingHand;
	}

	public void setUsingHand(String usingHand) {
		this.usingHand = usingHand;
	}

	public boolean isUsing() {
		return using;
	}

	public void setUsing(boolean using) {
		this.using = using;
	}

	public Status getEffectStatus() {
		return effectStatus;
	}

	public boolean isTwoHanded() {
		return isTwoHanded;
	}

	public void setTwoHanded(boolean isTwoHanded) {
		this.isTwoHanded = isTwoHanded;
	}

	public void setEffectStatus(Status effectStatus) {
		this.effectStatus = effectStatus;
	}

	public boolean isEmpty() {
		if (getItemPath().equals(EMPTY_ITEM)) {
			return true;
		} else {
			return false;
		}
	}
}
