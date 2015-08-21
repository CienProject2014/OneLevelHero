package com.mygdx.currentState;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.mygdx.enums.EventElementEnum;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 * 
 * @author Velmont
 * 
 */

public class EventInfo {
	private Map<String, NPC> npcMap;
	private Map<String, GameObject> gameObjectMap;
	private EventElementEnum currentEventElementType;
	private EventPacket currentSpecialEventInfo;
	private EventPacket currentNpcEventInfo;
	private GameObject currentGameObject;
	private Queue<EventPacket> specialEventQueue = new LinkedList<>();

	public EventPacket getCurrentNpcEventInfo() {
		return currentNpcEventInfo;
	}

	public void setCurrentNpcEventInfo(EventPacket currentNpcEventInfo) {
		this.currentNpcEventInfo = currentNpcEventInfo;
	}

	public Event getNpcEvent(EventPacket eventPacket) {
		return getNpc(eventPacket.getEventNpc()).getEvent(eventPacket.getEventNumber());
	}

	public NPC getNpc(String npcString) {
		return npcMap.get(npcString);
	}

	public Event getNpcEvent(String npcString, int eventNumber) {
		return getNpc(npcString).getEvent(eventNumber);
	}

	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}

	public void setCurrentGameObject(GameObject currentGameObject) {
		this.currentGameObject = currentGameObject;
	}

	public String getCurrentNpcName() {
		return currentNpcEventInfo.getEventNpc();
	}

	public Event getCurrentGameObjectEvent() {
		return currentGameObject.getObjectEvent();
	}

	public void setCurrentEventNpc(String npcString) {
		EventPacket eventPacket = new EventPacket();
		eventPacket.setEventNpc(npcString);
		setCurrentNpcEventInfo(eventPacket);
	}

	public void setCurrentEventNumber(int eventNumber) {
		currentNpcEventInfo.setEventNumber(eventNumber);
	}

	public EventElementEnum getCurrentEventElementType() {
		return currentEventElementType;
	}

	public void setCurrentEventElementType(EventElementEnum currentEventElementType) {
		this.currentEventElementType = currentEventElementType;
	}

	public Queue<EventPacket> getSpecialEventQueue() {
		return specialEventQueue;
	}

	public void setSpecialEventQueue(Queue<EventPacket> specialEventQueue) {
		this.specialEventQueue = specialEventQueue;
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

	public void setGameObjectMap(Map<String, GameObject> gameObjectMap) {
		this.gameObjectMap = gameObjectMap;
	}

	public EventPacket getCurrentSpecialEventInfo() {
		return currentSpecialEventInfo;
	}

	public void setCurrentSpecialEventInfo(EventPacket currentSpecialEventInfo) {
		this.currentSpecialEventInfo = currentSpecialEventInfo;
	}
}
