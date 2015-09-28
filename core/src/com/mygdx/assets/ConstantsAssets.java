package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.Array;
import com.mygdx.enums.JsonEnum;
import com.mygdx.util.JsonParser;

public class ConstantsAssets implements JsonAssetsInitializable {
	private Map<String, HashMap<String, Array<String>>> sceneConstantsMap = new HashMap<>();
	public Map<String, HashMap<String, Float>> uiConstantsMap = new HashMap<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void set(Map<String, String> jsonStringMap) {
		Map<String, HashMap> jsonLabelConstantsMap = JsonParser.parseMap(HashMap.class,
				jsonStringMap.get(JsonEnum.SCENE_CONSTANTS_JSON.toString()));
		for (Entry<String, HashMap> labelConstantsEntry : jsonLabelConstantsMap.entrySet()) {
			sceneConstantsMap.put(labelConstantsEntry.getKey(), labelConstantsEntry.getValue());
		}
		Map<String, HashMap> stageMap = JsonParser.parseMap(HashMap.class,
				jsonStringMap.get(String.valueOf(JsonEnum.UI_CONSTANTS)).toString());
		for (Entry<String, HashMap> stageEntry : stageMap.entrySet()) {
			uiConstantsMap.put(stageEntry.getKey(), stageEntry.getValue());
		}
	}

	public HashMap<String, Float> getUiConstants(String stageName) {
		return uiConstantsMap.get(stageName);
	}

	public HashMap<String, Array<String>> getSceneConstants(String labelListName) {
		return sceneConstantsMap.get(labelListName);
	}
}
