package com.mygdx.assets;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.model.jsonModel.AtlasUiFile;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class AtlasUiAssets implements FileAssetsInitializable {
	@Autowired
	private AssetsManager assetsManager;
	public Map<String, String> atlasUiMap = new HashMap<String, String>();

	public void set(Map<String, StringFile> filePathMap) {
		List<AtlasUiFile> atlasUiFileList = JsonParser.parseList(AtlasUiFile.class,
				filePathMap.get(JsonEnum.ATLAS_UI_PATH.toString()).loadFile());
		for (AtlasUiFile atlasUiFile : atlasUiFileList) {
			for (String element : atlasUiFile.getElement()) {
				atlasUiMap.put(element, atlasUiFile.loadFile());
				assetsManager.load(atlasUiMap.get(element), TextureAtlas.class);
			}
		}
		assetsManager.load("orig" + File.separator + "skill_" + "pack.atlas", TextureAtlas.class);
		assetsManager.load("orig" + File.separator + "save_" + "pack.atlas", TextureAtlas.class);
	}

	public TextureAtlas getAtlasRegionFile(String atlasUiFileString) {
		return assetsManager.get(atlasUiMap.get(atlasUiFileString), TextureAtlas.class);

	}

	public TextureRegionDrawable getAtlasUiFile(String atlasUiFileString) {
		return new TextureRegionDrawable(getAtlasRegionFile(atlasUiFileString).findRegion(atlasUiFileString));
	}
}
