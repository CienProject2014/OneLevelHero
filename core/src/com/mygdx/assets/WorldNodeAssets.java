package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.Village;

public class WorldNodeAssets implements JsonAssetsInitializable {
	public Map<String, Village> villageMap;

	public void set(Map<String, String> jsonStringMap) {
		villageMap = JsonParser.parseMap(Village.class,
				jsonStringMap.get(JsonEnum.VILLAGE_JSON.toString()));
	}

	public Village getVillage(String villageString) {
		return villageMap.get(villageString);
	}
}
