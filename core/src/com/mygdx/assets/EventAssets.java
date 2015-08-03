package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.Event;
import com.mygdx.model.EventPacket;
import com.mygdx.model.GameObject;
import com.mygdx.model.NPC;
import com.mygdx.model.StorySection;
import com.mygdx.util.JsonParser;

public class EventAssets implements JsonAssetsInitializable {
	private Map<String, NPC> npcMap;
	private Map<Integer, StorySection> storySectionMap;
	private Map<String, GameObject> gameObjectMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		npcMap = JsonParser.parseMap(NPC.class,
				jsonStringMap.get(JsonEnum.NPC_JSON.toString()));
		storySectionMap = JsonParser.parseMap(Integer.class,
				StorySection.class,
				jsonStringMap.get(JsonEnum.STORY_JSON.toString()));
		gameObjectMap = JsonParser.parseMap(GameObject.class,
				jsonStringMap.get(JsonEnum.GAME_OBJECT_JSON.toString()));
	}

	public StorySection getStorySection(int storySectionNumber) {
		return storySectionMap.get(String.valueOf(storySectionNumber));
	}

	public GameObject getGameObject(String gameObjectString) {
		return gameObjectMap.get(gameObjectString);
	}

	public NPC getNpc(String npcString) {
		return npcMap.get(npcString);
	}

	public Map<String, NPC> getNpcMap() {
		return npcMap;
	}

	public void setNpcMap(Map<String, NPC> npcMap) {
		this.npcMap = npcMap;
	}

	public Event getEvent(String npcString, int eventNumber) {
		return getNpc(npcString).getEvent(eventNumber);
	}

	public Event getEvent(EventPacket eventPacket) {
		return getNpc(eventPacket.getEventNpc()).getEvent(
				eventPacket.getEventNumber());
	}

	public Map<Integer, StorySection> getStorySectionMap() {
		return storySectionMap;
	}

	public void setStorySectionMap(Map<Integer, StorySection> storySectionMap) {
		this.storySectionMap = storySectionMap;
	}

}
