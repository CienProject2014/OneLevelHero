package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.WorldNode;

public class WorldMapAssets implements JsonAssetsInitializable {
	private Map<String, WorldNode> worldNodeInfoMap;

	public void set(Map<String, String> jsonStringMap) {
		worldNodeInfoMap = JsonParser.parseMap(WorldNode.class,
				jsonStringMap.get(JsonEnum.WORLDMAP_JSON.toString()));
	}

	public WorldNode getWorldNodeInfo(String worldNodeString) {
		return worldNodeInfoMap.get(worldNodeString);
	}
}
