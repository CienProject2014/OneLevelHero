package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;

public class FrameSheet {
	private Texture texture;
	private int frameNumber;
	private int row;
	private int column;
	
	public FrameSheet(SheetFile file) {
		this.texture = file.getFile();
		this.frameNumber = file.getFrameNumber();
		this.row = file.getRow();
		this.column = file.getColumn();
	}

	public Texture getTexture() {
		return texture;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	

}
