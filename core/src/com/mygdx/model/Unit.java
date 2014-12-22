package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.resource.Assets;

public class Unit {
	private String unitName;
	private Texture faceImage;
	private String faceImagePath;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Texture getFaceImage() {
		if (faceImage == null)
			faceImage = Assets.imageFileMap.get(faceImagePath);
		return faceImage;
	}

	public void setFaceImage(Texture faceImage) {
		this.faceImage = faceImage;
	}

	public String getFaceImagePath() {
		return faceImagePath;
	}

	public void setFaceImagePath(String faceImagePath) {
		this.faceImagePath = faceImagePath;
	}
}
