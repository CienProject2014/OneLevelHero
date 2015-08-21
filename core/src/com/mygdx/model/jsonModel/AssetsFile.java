package com.mygdx.model.jsonModel;

import com.mygdx.manager.AssetsManager;

@SuppressWarnings("hiding")
public interface AssetsFile<String> {
	public String loadFile(AssetsManager assetsManager);

	public String getTestFile();
}
