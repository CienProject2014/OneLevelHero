package com.mygdx.model;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AtlasUiFile implements AssetsFile<TextureAtlas> {
	private List<String> element;
	private String name;
	private String filePath;;

	@Override
	public TextureAtlas getFile() {
		TextureAtlas file = new TextureAtlas(filePath);
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

}
