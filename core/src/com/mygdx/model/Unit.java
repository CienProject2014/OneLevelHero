package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Unit {
	private String unitName;
	private Image faceImage;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Image getFaceImage() {
		return faceImage;
	}

	public void setFaceImage(Image faceImage) {
		this.faceImage = faceImage;
	}
}
