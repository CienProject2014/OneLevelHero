package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.model.location.WorldNode;
import com.mygdx.util.JsonParser;

public class WorldMapAssets implements JsonAssetsInitializable {
	private Map<String, WorldNode> worldNodeInfoMap;

	public void set(Map<String, String> jsonStringMap) {
		worldNodeInfoMap = JsonParser.parseMap(WorldNode.class, jsonStringMap.get(JsonEnum.WORLDMAP_JSON.toString()));
	}

	public WorldNode getWorldNodeInfo(String worldNodeString) {
		return worldNodeInfoMap.get(worldNodeString);
	}

	public WorldNodeEnum.NodeType getNodeType(String worldNodeString) {
		return worldNodeInfoMap.get(worldNodeString).getNodeType();
	}
}
