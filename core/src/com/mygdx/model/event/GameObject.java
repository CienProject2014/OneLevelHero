package com.mygdx.model.event;

import com.mygdx.enums.GameObjectEnum;

public class GameObject extends EventElement {
	private GameObjectEnum objectType;
	private boolean isPressed;
	public GameObjectEnum getObjectType() {
		return objectType;
	}
	public void setObjectType(GameObjectEnum objectType) {
		this.objectType = objectType;
	}
	public boolean isPressed() {
		return isPressed;
	}
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	public int getPositionIndex() {
		return positionIndex;
	}
	public void setPositionIndex(int positionIndex) {
		this.positionIndex = positionIndex;
	}
}
