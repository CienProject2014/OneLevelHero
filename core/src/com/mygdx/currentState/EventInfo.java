package com.mygdx.currentState;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.EventAssets;
import com.mygdx.model.Event;
import com.mygdx.model.EventPacket;
import com.mygdx.model.GameObject;
import com.mygdx.model.NPC;

/**
 * npc정보, eventNumber, greeting여부 정보를 갖고있음
 *
 * @author Velmont
 *
 */

public class EventInfo {
	@Autowired
	private EventAssets eventAssets;

	private EventPacket currentEventInfo;
	private GameObject currentGameObject;

	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}

	public void setCurrentGameObject(GameObject gameObject) {
		this.currentGameObject = gameObject;
	}

	public NPC getCurrentNpc() {
		String npcName = currentEventInfo.getEventNpc();
		return eventAssets.getNpc(npcName);
	}

	public Event getCurrentEvent() {
		return eventAssets.getEvent(currentEventInfo.getEventNpc(),
				currentEventInfo.getEventNumber());
	}

	public void setCurrentEventNpc(String npcString) {
		EventPacket eventPacket = new EventPacket();
		eventPacket.setEventNpc(npcString);
		eventPacket.setGreeting(true);
		setCurrentEventInfo(eventPacket);
	}

	public void setCurrentEventNumber(int eventNumber) {
		currentEventInfo.setEventNumber(eventNumber);
	}

	public EventPacket getCurrentEventInfo() {
		return currentEventInfo;
	}

	public void setCurrentEventInfo(EventPacket currentEventInfo) {
		this.currentEventInfo = currentEventInfo;
	}

}
