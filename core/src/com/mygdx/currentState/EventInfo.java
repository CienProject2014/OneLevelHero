package com.mygdx.currentState;

import java.util.Map;

import com.mygdx.enums.EventElementEnum;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;
import com.mygdx.model.event.Quest;
import com.mygdx.model.location.Building;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 * 
 * @author Velmont
 * 
 */

public class EventInfo {
	private Map<String, NPC> npcMap;
	private Map<String, GameObject> gameObjectMap;
	private Map<String, Quest> questMap;
	private Map<String, NPC> mainStoryMap;
	private EventElementEnum currentEventElementType;
	private Event currentStoryEvent;
	private Event currentNpcEvent;
	private GameObject currentGameObject;
	private NPC currentNpc;
	private Building currentBuildingInfo;

	public EventElementEnum getCurrentEventElementType() {
		return currentEventElementType;
	}
	public void setCurrentEventElementType(EventElementEnum currentEventElementType) {
		this.currentEventElementType = currentEventElementType;
	}

	public void setCurrentNpcEvent(EventPacket eventPacket) {
		this.currentNpcEvent = getNpc(eventPacket.getEventElement()).getEvent(eventPacket.getEventNumber());
	}

	public Event getCurrentNpcEvent() {
		return currentNpcEvent;
	}

	public void setCurrentStoryEvent(EventPacket eventPacket) {
		this.currentStoryEvent = getMainStory(eventPacket.getEventElement()).getEvent(eventPacket.getEventNumber());
	}

	public Event getCurrentStoryEvent() {
		return currentStoryEvent;
	}

	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}

	public void setCurrentGameObject(GameObject currentGameObject) {
		this.currentGameObject = currentGameObject;
	}

	private NPC getNpc(String npcString) {
		return npcMap.get(npcString);
	}

	private NPC getMainStory(String mainStory) {
		return mainStoryMap.get(mainStory);
	}

	public Event getCurrentGameObjectEvent() {
		return currentGameObject.getObjectEvent();
	}

	public Map<String, Quest> getQuestMap() {
		return questMap;
	}

	public Map<String, NPC> getNpcMap() {
		return npcMap;
	}
	public void setNpcMap(Map<String, NPC> npcMap) {
		this.npcMap = npcMap;
	}
	public void setQuestMap(Map<String, Quest> questMap) {
		this.questMap = questMap;
	}
	public Map<String, GameObject> getGameObjectMap() {
		return gameObjectMap;
	}
	public void setGameObjectMap(Map<String, GameObject> gameObjectMap) {
		this.gameObjectMap = gameObjectMap;
	}
	public NPC getCurrentNpc() {
		return currentNpc;
	}
	public void setCurrentNpc(NPC currentNpc) {
		this.currentNpc = currentNpc;
	}
	public Map<String, NPC> getMainStoryMap() {
		return mainStoryMap;
	}
	public void setMainStoryMap(Map<String, NPC> mainStoryMap) {
		this.mainStoryMap = mainStoryMap;
	}
	public Building getCurrentBuildingInfo() {
		return currentBuildingInfo;
	}
	public void setCurrentBuildingInfo(Building currentBuildingInfo) {
		this.currentBuildingInfo = currentBuildingInfo;
	}
}
