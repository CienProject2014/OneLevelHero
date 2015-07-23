package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.model.WorldNode;
import com.mygdx.util.JsonParser;

public class WorldMapAssets implements JsonAssetsInitializable {
	private Map<String, WorldNode> worldNodeInfoMap;

	public void set(Map<String, String> jsonStringMap) {
		worldNodeInfoMap = JsonParser.parseMap(WorldNode.class,
				jsonStringMap.get(JsonEnum.WORLDMAP_JSON.toString()));
	}

	public WorldNode getWorldNodeInfo(String worldNodeString) {
		return worldNodeInfoMap.get(worldNodeString);
	}

	public PlaceEnum getWorldNodeType(String worldNodeString) {
		return worldNodeInfoMap.get(worldNodeString).getNodeType();
	}
}
