package com.mygdx.manager;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.eventTrigger.EventTrigger;
import com.mygdx.factory.EventTriggerFactory;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.factory.StageFactory;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventElement;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.EventScene;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;
import com.mygdx.model.location.Building;
import com.mygdx.model.unit.Hero;

/**
 * CHAT, SELECT 등의 이벤트정보를 세팅해주는 클래스 CHAT 이벤트의 경우 Iterator를 돌려서 EventScene을
 * CHAT이벤트가 끝날때까지 리턴해준다.
 * 
 * @author Velmont
 * 
 */
public class EventManager {
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private EventTriggerFactory eventTriggerFactory;

	public void triggerEvent(EventElementEnum eventElementType, EventPacket eventPacket) {
		setCurrentEvent(eventElementType, eventPacket);
		triggerCurrentEvent();
	}

	public void triggerCurrentEvent() {
		EventTypeEnum eventType = getCurrentEvent().getEventType();
		EventTrigger eventTrigger = eventTriggerFactory.getEventTrigger(eventType);
		setEventOpen(getCurrentEvent());
		eventTrigger.triggerEvent(getCurrentEvent().getEventParameter());
	}

	public EventElement getCurrentEventElement() {
		switch (eventInfo.getCurrentEventElementType()) {
			case NPC :
				return eventInfo.getCurrentNpc();
			case GAME_OBJECT :
				return eventInfo.getCurrentGameObject();
			default :
				Gdx.app.log("EventManager", "EventElement정보 오류");
				return null;
		}
	}

	public void setCurrentEvent(EventElementEnum eventElementType, EventPacket eventPacket) {
		eventInfo.setCurrentEventElementType(eventElementType);
		switch (eventElementType) {
			case NPC :
				eventInfo.setCurrentNpcEvent(eventPacket);
				break;
			case STORY :
				eventInfo.setCurrentStoryEvent(eventPacket);
				break;
			default :
				Gdx.app.log("EventManager", "eventElementType정보 오류 - " + eventElementType);
				break;
		}
	}

	public NPC getCurrentNpc() {
		return eventInfo.getCurrentNpc();
	}

	public void setCurrentNpc(String npcName) {
		NPC npc = eventInfo.getNpcMap().get(npcName);
		eventInfo.setCurrentEventElementType(EventElementEnum.NPC);
		eventInfo.setCurrentNpc(npc);
	}

	public GameObject getCurrentGameObject() {
		return eventInfo.getCurrentGameObject();
	}

	public void setCurrentGameObject(GameObject gameObject) {
		eventInfo.setCurrentEventElementType(EventElementEnum.GAME_OBJECT);
		eventInfo.setCurrentGameObject(gameObject);
	}

	public void setTargetBuildingInfo(Building buildingInfo) {
		eventInfo.setCurrentBuildingInfo(buildingInfo);
	}

	public Building getTargetBuildingInfo() {
		return eventInfo.getCurrentBuildingInfo();
	}

	public Event getCurrentEvent() {
		switch (eventInfo.getCurrentEventElementType()) {
			case NPC :
				return eventInfo.getCurrentNpcEvent();
			case STORY :
				return eventInfo.getCurrentStoryEvent();
			default :
				Gdx.app.log("EventManager", "eventElement정보 오류" + eventInfo.getCurrentEventElementType());
				return null;
		}
	}

	public void setCurrentStoryEvent(EventPacket eventPacket) {
		eventInfo.setCurrentStoryEvent(eventPacket);
	}

	public void setCurrentNpcEvent(EventPacket eventPacket) {
		eventInfo.setCurrentNpcEvent(eventPacket);
	}

	public void setNpcMap(Map<String, NPC> npcMap) {
		eventInfo.setNpcMap(npcMap);
	}

	public void setGameObjectMap(Map<String, GameObject> gameObjectMap) {
		eventInfo.setGameObjectMap(gameObjectMap);
	}

	public void finishEvent() {
		if (getCurrentEvent().getEventState() == EventStateEnum.OPENED) {
			getCurrentEvent().setEventState(EventStateEnum.CLEARED);
		}
	}

	public void setEventOpen(Event currentEvent) {
		if (currentEvent.getEventState().equals(EventStateEnum.NOT_OPENED)) {
			currentEvent.setEventState(EventStateEnum.OPENED);
		}
	}

	public boolean isEventOpen(Event event) {
		if (event.getEventState().equals(EventStateEnum.ALWAYS_OPEN)
				|| event.getEventState().equals(EventStateEnum.OPENED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEventNotOpened(Event event) {
		if (event.getEventState().equals(EventStateEnum.NOT_OPENED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEventCleared(Event event) {
		if (event.getEventState().equals(EventStateEnum.CLEARED)) {
			return true;
		} else {
			return false;
		}
	}

	public Event getStoryEvent(EventPacket eventPacket) {
		return eventInfo.getMainStoryMap().get(eventPacket.getEventElement()).getEvent(eventPacket.getEventNumber());
	}

	public Event getNpcEvent(EventPacket eventPacket) {
		return eventInfo.getNpcMap().get(eventPacket.getEventElement()).getEvent(eventPacket.getEventNumber());
	}

	public void setMainStoryMap(Map<String, NPC> mainStoryMap) {
		eventInfo.setMainStoryMap(mainStoryMap);
	}

	public void setCurrentChatScenes(ArrayList<EventScene> eventScenes) {
		eventInfo.setCurrentEventScenes(eventScenes);
	}

	public ArrayList<EventScene> getCurrentChatScenes() {
		return eventInfo.getCurrentEventScenes();
	}

	public void setHeroMap(Map<String, Hero> heroMap) {
		eventInfo.setHeroMap(heroMap);
	}

	public Map<String, Hero> getHeroMap() {
		return eventInfo.getHeroMap();
	}
}
