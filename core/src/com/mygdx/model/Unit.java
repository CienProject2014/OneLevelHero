package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.resource.Assets;

public class Unit {
	private String unitName;
	private Image faceImage;
	private String faceImagePath;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Image getFaceImage() {
		if (faceImage == null)
			faceImage = (Image) Assets.imageFileList.get(faceImagePath);
		return faceImage;
	}

	public void setFaceImage(Image faceImage) {
		this.faceImage = faceImage;
	}

	public String getFaceImagePath() {
		return faceImagePath;
	}

	public void setFaceImagePath(String faceImagePath) {
		this.faceImagePath = faceImagePath;
	}
}
