package com.mygdx.model.jsonModel;

public class MusicStringFile implements AssetsFile<String> {
	private String file;
	private String filePath;

	@Override
	public String getTestFile() {
		return null;
	}

	@Override
	public String loadFile() {
		file = filePath;
		return file;
	}
}
