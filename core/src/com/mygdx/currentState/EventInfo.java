package com.mygdx.currentState;

import com.mygdx.enums.EventElementEnum;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.GameObject;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 * 
 * @author Velmont
 * 
 */

public class EventInfo {

	private EventElementEnum currentEventElementType;
	private EventPacket currentEventInfo;
	private GameObject currentGameObject;

	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}

	public void setCurrentGameObject(GameObject currentGameObject) {
		this.currentGameObject = currentGameObject;
	}

	public String getCurrentNpcName() {
		return currentEventInfo.getEventNpc();
	}

	public Event getCurrentGameObjectEvent() {
		return currentGameObject.getObjectEvent();
	}

	public void setCurrentEventNpc(String npcString) {
		EventPacket eventPacket = new EventPacket();
		eventPacket.setEventNpc(npcString);
		setCurrentEventInfo(eventPacket);
	}

	public void setCurrentEventNumber(int eventNumber) {
		currentEventInfo.setEventNumber(eventNumber);
	}

	public EventPacket getEventPacket() {
		return currentEventInfo;
	}

	public EventPacket getCurrentEventInfo() {
		return currentEventInfo;
	}

	public void setCurrentEventInfo(EventPacket currentEventInfo) {
		this.currentEventInfo = currentEventInfo;
	}

	public EventElementEnum getCurrentEventElementType() {
		return currentEventElementType;
	}

	public void setCurrentEventElementType(EventElementEnum currentEventElementType) {
		this.currentEventElementType = currentEventElementType;
	}

}
