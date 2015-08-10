package com.mygdx.model.item;

public class HandGrip extends Equipment {
	private boolean isTwoHanded;

	public boolean isTwoHanded() {
		return isTwoHanded;
	}

	public void setTwoHanded(boolean isTwoHanded) {
		this.isTwoHanded = isTwoHanded;
	}
}
