package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class SheetFile implements AssetsFile<Texture> {
	private String filePath;
	private int frameNumber;
	private int row;
	private int column;
	private Texture file;

	@Override
	public Texture getFile() {
		file = new Texture(Gdx.files.internal(filePath));
		return file;
	}

	@Override
	public Texture getTestFile() {
		file = new Texture(Gdx.files.internal("../android/assets/" + filePath));
		return file;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}


}
