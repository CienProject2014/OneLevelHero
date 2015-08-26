package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;
import com.mygdx.model.event.StorySection;
import com.mygdx.util.JsonParser;

public class EventAssets implements JsonAssetsInitializable {
	private Map<String, NPC> npcMap;
	private Map<Integer, StorySection> storySectionMap;
	private Map<String, GameObject> gameObjectMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		npcMap = JsonParser.parseMap(NPC.class, jsonStringMap.get(String.valueOf(JsonEnum.NPC_JSON)));
		storySectionMap = JsonParser.parseMap(Integer.class, StorySection.class,
				jsonStringMap.get(String.valueOf(JsonEnum.STORY_JSON)));
		gameObjectMap = JsonParser.parseMap(GameObject.class, jsonStringMap.get(String.valueOf(JsonEnum.GAME_OBJECT_JSON)));
	}

	public StorySection getStorySection(int storySectionNumber) {
		return storySectionMap.get(String.valueOf(storySectionNumber));
	}

	public GameObject getGameObject(String gameObjectString) {
		return gameObjectMap.get(gameObjectString);
	}

	public Map<String, NPC> getNpcMap() {
		return npcMap;
	}

	public void setNpcMap(Map<String, NPC> npcMap) {
		this.npcMap = npcMap;
	}

	public Map<String, GameObject> getGameObjectMap() {
		return gameObjectMap;
	}

	public Map<Integer, StorySection> getStorySectionMap() {
		return storySectionMap;
	}

	public void setStorySectionMap(Map<Integer, StorySection> storySectionMap) {
		this.storySectionMap = storySectionMap;
	}

}
