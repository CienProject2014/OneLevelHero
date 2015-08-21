package com.mygdx.model.jsonModel;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.assets.StaticAssets;

public class AtlasUiFile implements AssetsFile<String> {
	private List<String> element;
	private String name;
	private String filePath;

	@Override
	public String loadFile() {
		StaticAssets.assetManager.load(filePath, TextureAtlas.class);
		return filePath;
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
	public String getTestFile() {
		// TODO Auto-generated method stub
		return null;
	}
}
