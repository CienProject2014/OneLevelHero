package com.mygdx.manager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.factory.StageFactory;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.EventScene;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;
import com.mygdx.model.event.Reward;

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
	private EventCheckManager eventCheckManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private EventInfo eventInfo;

	private Iterator<EventScene> eventSceneIterator;
	private final int eventPlusRule = 1;

	public Queue<EventPacket> getSpecialEventQueue() {
		return eventInfo.getSpecialEventQueue();
	}

	public void doStoryEvent(EventTypeEnum eventType) {
		setCurrentEventElementType(EventElementEnum.NPC);
		switch (eventType) {
			case BATTLE :
				battleManager.startBattle(unitAssets.getMonster(getCurrentNpcEvent().getEventComponent().get(0)));
				battleManager.setEventBattle(true);
				screenFactory.show(ScreenEnum.BATTLE);
				break;
			case NEXT_SECTION :
				storySectionManager.setNewStorySectionAndPlay(Integer.valueOf(getCurrentNpcEvent().getEventComponent()
						.get(0)));
				break;
			case MOVE_NODE :
				positionManager.setCurrentNodeName(getCurrentNpcEvent().getEventComponent().get(0));
				movingManager.goCurrentPosition();
				storySectionManager.runStorySequence();
				break;
			case BATTLE_END :
				battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				battleManager.setEventBattle(false);
				storySectionManager.runStorySequence();
				break;
			case MOVE_SUB_NODE :
				positionManager.setCurrentSubNodeName(getCurrentNpcEvent().getEventComponent().get(0));
				positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
				movingManager.goCurrentPosition();
				storySectionManager.runStorySequence();
				break;
			case PASS_TIME :
				timeManager.plusMinute(Integer.parseInt(getCurrentNpcEvent().getEventComponent().get(0)));
				storySectionManager.runStorySequence();
				break;
			case MUSIC :
				musicManager.setEventMusicAndPlay();
				storySectionManager.runStorySequence();
				break;
			default :
				screenFactory.show(ScreenEnum.EVENT);
				break;
		}
	}

	public Event getCurrentElementEvent() {
		switch (getCurrentEventElementType()) {
			case NPC :
				return getCurrentNpcEvent();
			case GAME_OBJECT :
				return getCurrentGameObject().getObjectEvent();
			case SPECIAL :
				return getCurrentSpecialEvent();
			default :
				Gdx.app.log("EventManager", "EventElementType 정보 오류");
				return getCurrentNpcEvent();
		}
	}
	public void doSpecialEvent(Event event) {
		switch (event.getEventType()) {
			case DONT_GO_BUILDING :
				setCurrentEventElementType(EventElementEnum.SPECIAL);
				screenFactory.show(ScreenEnum.EVENT);
				break;
			default :
				Gdx.app.log("EventManager", "SpecialEvent EventType정보 오류");
				break;
		}
	}

	public void triggerSpecialEvent(EventTypeEnum eventType, String componentString) {
		Gdx.app.log("EventManager", "trigSpecialEvent");
		if (!eventInfo.getSpecialEventQueue().isEmpty()) {
			Iterator<EventPacket> specialEventQueueIterator = eventInfo.getSpecialEventQueue().iterator();
			while (specialEventQueueIterator.hasNext()) {
				EventPacket eventPacket = specialEventQueueIterator.next();
				Event specialEvent = getNpcEvent(eventPacket);
				if (specialEvent.getEventType().equals(eventType)) {
					if (eventCheckManager.checkSameWithComponentList(specialEvent.getEventComponent(), componentString)) {
						setCurrentSpecialEventInfo(eventPacket);
						doSpecialEvent(specialEvent);
					}
				}
			}
		}
	}
	private void setCurrentSpecialEventInfo(EventPacket eventPacket) {
		eventInfo.setCurrentSpecialEventInfo(eventPacket);
	}

	public EventElementEnum getCurrentEventElementType() {
		return eventInfo.getCurrentEventElementType();
	}

	public void setCurrentEventElementType(EventElementEnum eventElementType) {
		eventInfo.setCurrentEventElementType(eventElementType);
	}

	public NPC getCurrentNpc() {
		return eventInfo.getNpc(eventInfo.getCurrentNpcName());
	}

	public Stage getSceneEvent() {
		Event currentEvent;
		if (eventInfo.getCurrentEventElementType().equals(EventElementEnum.NPC)) {
			currentEvent = getCurrentNpcEvent();
		} else if (eventInfo.getCurrentEventElementType().equals(EventElementEnum.GAME_OBJECT)) {
			currentEvent = getCurrentGameObject().getObjectEvent();
		} else {
			currentEvent = getCurrentSpecialEvent();
		}
		switch (currentEvent.getEventType()) {
			case CHAT :
			case CREDIT :
			case SELECT_COMPONENT :
			case SELECT_EVENT :
			case DONT_GO_BUILDING :
				return getChatScene(currentEvent);
			default :
				Gdx.app.error("EventManager", "EventTypeEnum 정보가 없습니다.");
				throw new NullPointerException();
		}
	}

	private Event getCurrentSpecialEvent() {
		return getNpcEvent(eventInfo.getCurrentSpecialEventInfo());
	}

	private Stage getChatScene(Event currentEvent) {
		Iterator<EventScene> eventSceneIterator = getEventSceneIterator(currentEvent);
		if (eventSceneIterator.hasNext()) {
			Stage eventStage = stageFactory.makeEventStage(eventSceneIterator);
			return eventStage;
		} else {
			Gdx.app.log("EventManager", "EventInfo - SceneIterator 주입 에러");
			return null;
		}
	}

	public EventPacket getCurrentNpcEventPacket() {
		return eventInfo.getCurrentNpcEventInfo();
	}

	public EventScene getGameObjectEventScene() {
		EventScene eventScene = eventInfo.getCurrentGameObjectEvent().getEventScenes().get(0);
		if (eventInfo.getCurrentGameObjectEvent().getRewards() != null) {
			addEventRewardQueue(eventInfo.getCurrentGameObject().getObjectEvent().getRewards());
		}
		return eventScene;
	}

	public Iterator<EventScene> getEventSceneIterator(Event currentEvent) {
		eventSceneIterator = currentEvent.getEventSceneIterator();
		if (currentEvent.getRewards() != null) {
			addEventRewardQueue(currentEvent.getRewards());
		}
		return eventSceneIterator;
	}

	private void addEventRewardQueue(List<Reward> rewardList) {
		for (Reward reward : rewardList) {
			if (reward.getRewardState() != RewardStateEnum.CLEARED) {
				rewardManager.addEventReward(reward);
			}
		}
	}

	public Event getCurrentNpcEvent() {
		EventPacket eventPacket = eventInfo.getCurrentNpcEventInfo();
		return eventInfo.getNpcEvent(eventPacket.getEventNpc(), eventPacket.getEventNumber());
	}

	public void setCurrentGameObject(GameObject gameObject) {
		eventInfo.setCurrentGameObject(gameObject);
	}

	public GameObject getCurrentGameObject() {
		return eventInfo.getCurrentGameObject();
	}

	public void setNpcMap(Map<String, NPC> npcMap) {
		eventInfo.setNpcMap(npcMap);
	}

	public void setGameObjectMap(Map<String, GameObject> gameObjectMap) {
		eventInfo.setGameObjectMap(gameObjectMap);
	}

	public void finishNpcEvent() {
		if (getCurrentNpcEvent().getEventState() == EventStateEnum.OPENED) {
			getCurrentNpcEvent().setEventState(EventStateEnum.CLEARED);
		}
	}

	public void finishGameObjectEvent() {
		if (eventInfo.getCurrentGameObjectEvent().getEventState() == EventStateEnum.OPENED) {
			eventInfo.getCurrentGameObjectEvent().setEventState(EventStateEnum.CLEARED);
		}
	}

	public void setCurrentNpcEventInfo(EventPacket eventPacket) {
		eventInfo.setCurrentNpcEventInfo(eventPacket);
	}

	public void setCurrentEventNumber(int eventNumber) {
		eventInfo.setCurrentEventNumber(eventNumber);
	}

	public void setCurrentEventNpc(String npcName) {
		eventInfo.setCurrentEventNpc(npcName);
		eventInfo.setCurrentEventNumber(1); // FIXME
	}

	public void setEventOpen(Event currentEvent) {
		if (currentEvent.getEventState().equals(EventStateEnum.NOT_OPENED)) {
			currentEvent.setEventState(EventStateEnum.OPENED);
		}
	}

	public void setGreeting(boolean isGreeting) {
		eventInfo.getCurrentNpcEventInfo().setGreeting(isGreeting);
	}

	public boolean isGreeting() {
		return eventInfo.getCurrentNpcEventInfo().isGreeting();
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

	public Event getNpcEvent(EventPacket eventPacket) {
		return eventInfo.getNpcEvent(eventPacket);
	}

	public void triggerComponentEvent(int index) {
		String eventComponent = getCurrentNpcEvent().getEventComponent().get(index);
		if (getCurrentNpcEvent().getEventTarget() != null) {
			NPC npc = eventInfo.getNpc(getCurrentNpcEvent().getEventTarget());
			if (eventCheckManager.checkSameWithComponent(eventComponent, npc.getEvent(index + eventPlusRule)
					.getEventName())) {
				setCurrentEventNpc(getCurrentNpcEvent().getEventTarget());
				setCurrentEventNumber(index + eventPlusRule); // 알고리즘이필요함
				screenFactory.show(ScreenEnum.EVENT);
			}
		}
	}

}
