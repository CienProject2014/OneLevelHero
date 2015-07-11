package com.mygdx.assets;

import java.util.Map;

import com.mygdx.model.JsonStringFile;

public interface FileAssetsInitializable {
	public void set(Map<String, JsonStringFile> filePathMap);
}
