package com.mygdx.model.jsonModel;

public class MusicFile implements AssetsFile<String> {
	private String filePath;

	@Override
	public String loadFile() {
		return filePath;
	}

	@Override
	public String getTestFile() {
		return null;
	}
}
