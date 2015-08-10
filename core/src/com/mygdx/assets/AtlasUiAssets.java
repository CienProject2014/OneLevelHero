package com.mygdx.assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.jsonModel.AtlasUiFile;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class AtlasUiAssets implements FileAssetsInitializable {
	public Map<String, TextureRegionDrawable> atlasUiMap = new HashMap<String, TextureRegionDrawable>();

	public void set(Map<String, StringFile> filePathMap) {
		List<AtlasUiFile> atlasUiFileList = JsonParser.parseList(
				AtlasUiFile.class,
				filePathMap.get(JsonEnum.ATLAS_UI_PATH.toString()).loadFile());
		for (AtlasUiFile atlasUiFile : atlasUiFileList)
			for (String element : atlasUiFile.getElement())
				atlasUiMap.put(element, new TextureRegionDrawable(
						atlasUiFile.loadFile().findRegion(element)));
	}

	public TextureRegionDrawable getAtlasUiFile(String atlasUiFileString) {
		return atlasUiMap.get(atlasUiFileString);
	}
}
