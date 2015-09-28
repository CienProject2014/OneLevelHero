package com.mygdx.assets;

import java.util.Map;

import com.mygdx.model.jsonModel.StringFile;

public interface FileAssetsInitializable {
	public void set(Map<String, StringFile> filePathMap);
}
