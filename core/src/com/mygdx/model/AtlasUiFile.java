package com.mygdx.model;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class AtlasUiFile implements Disposable, AssetsFile<TextureAtlas> {
	private List<String> element;
	private String name;
	private String filePath;;
	private TextureAtlas file;

	@Override
	public TextureAtlas getFile() {
		file = new TextureAtlas(filePath);
		return file;
	}

	@Override
	public TextureAtlas getTestFile() {
		file = new TextureAtlas("../android/assets/" + filePath);
		return file;
	}

	public List<String> getElement() {
		return element;
	}

	public void setElement(List<String> element) {
		this.element = element;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void dispose() {
		Gdx.app.log("TextureFile", "Dispose");
		file.dispose();
	}
}
