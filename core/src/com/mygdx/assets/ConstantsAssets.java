package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.Array;
import com.mygdx.enums.JsonEnum;
import com.mygdx.util.JsonParser;

public class ConstantsAssets implements JsonAssetsInitializable {
	private Map<String, HashMap<String, Array<String>>> sceneConstantsMap = new HashMap<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void set(Map<String, String> jsonStringMap) {
		Map<String, HashMap> jsonLabelConstantsMap = JsonParser.parseMap(HashMap.class,
				jsonStringMap.get(JsonEnum.SCENE_CONSTANTS_JSON.toString()));
		for (Entry<String, HashMap> labelConstantsEntry : jsonLabelConstantsMap.entrySet()) {
			sceneConstantsMap.put(labelConstantsEntry.getKey(), labelConstantsEntry.getValue());
		}
	}

	public HashMap<String, Array<String>> getSceneConstants(String labelListName) {
		return sceneConstantsMap.get(labelListName);
	}
}
