package com.mygdx.manager;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.EventAssets;
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
	private EventInfo eventInfo;
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
	private EventAssets eventAssets;
	@Autowired
	private TimeManager timeManager;

	private Iterator<EventScene> eventSceneIterator;
	private final int eventPlusRule = 1;

	public void doStoryEvent(EventTypeEnum eventType) {
		setCurrentEventElementType(EventElementEnum.NPC);
		switch (eventType) {
			case BATTLE :
				battleManager.startBattle(unitAssets
						.getMonster(getCurrentEvent().getEventComponent()
								.get(0)));
				screenFactory.show(ScreenEnum.BATTLE);
				break;
			case NEXT_SECTION :
				storySectionManager.setNewStorySectionAndPlay(Integer
						.valueOf(getCurrentEvent().getEventComponent().get(0)));
				break;
			case MOVE_NODE :
				positionManager.setCurrentNodeName(getCurrentEvent()
						.getEventComponent().get(0));
				movingManager.goCurrentPosition();
				storySectionManager.runStorySequence();
				break;
			case BATTLE_END :
				battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
				storySectionManager.runStorySequence();
				break;
			case MOVE_SUB_NODE :
				positionManager.setCurrentSubNodeName(getCurrentEvent()
						.getEventComponent().get(0));
				positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
				movingManager.goCurrentPosition();
				storySectionManager.runStorySequence();
				break;
			case PASS_TIME :
				timeManager.plusMinute(Integer.parseInt(getCurrentEvent()
						.getEventComponent().get(0)));
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

	public EventElementEnum getCurrentEventElementType() {
		return eventInfo.getCurrentEventElementType();
	}

	public void setCurrentEventElementType(EventElementEnum eventElementType) {
		eventInfo.setCurrentEventElementType(eventElementType);
	}

	public Stage getSceneEvent() {
		Event currentEvent = eventInfo.getCurrentNpcEvent();
		switch (currentEvent.getEventType()) {
			case CHAT :
			case CREDIT :
			case SELECT_COMPONENT :
			case SELECT_EVENT :
				return getChatScene();
			default :
				Gdx.app.error("EventManager", "EventTypeEnum 정보가 없습니다.");
				throw new NullPointerException();
		}
	}

	private Stage getChatScene() {
		Iterator<EventScene> eventSceneIterator = getEventSceneIterator();
		if (eventSceneIterator.hasNext()) {
			Stage eventStage = stageFactory.makeEventStage(eventSceneIterator);
			return eventStage;
		} else {
			Gdx.app.log("EventManager", "EventInfo - SceneIterator 주입 에러");
			return null;
		}
	}

	public EventPacket getCurrentEventPacket() {
		return eventInfo.getCurrentEventInfo();
	}

	public EventScene getGameObjectEventScene() {
		EventScene eventScene = eventInfo.getCurrentGameObjectEvent()
				.getEventScenes().get(0);
		if (eventInfo.getCurrentGameObjectEvent().getRewards() != null) {
			addEventRewardQueue(eventInfo.getCurrentGameObject()
					.getObjectEvent().getRewards());
		}
		return eventScene;
	}

	public Iterator<EventScene> getEventSceneIterator() {
		eventSceneIterator = eventInfo.getCurrentNpcEvent()
				.getEventSceneIterator();
		if (eventInfo.getCurrentNpcEvent().getRewards() != null) {
			addEventRewardQueue(eventInfo.getCurrentNpcEvent().getRewards());
		}
		return eventSceneIterator;
	}

	private void addEventRewardQueue(List<Reward> rewardList) {
		for (Reward reward : rewardList) {
			if (reward.getRewardState() == RewardStateEnum.NOT_CLEARED) {
				rewardManager.addEventReward(reward);
			}
		}
	}

	public NPC getCurrentNpc() {
		return eventInfo.getCurrentNpc();
	}

	public Event getCurrentEvent() {
		return eventInfo.getCurrentNpcEvent();
	}

	public void setCurrentGameObject(GameObject gameObject) {
		eventInfo.setCurrentGameObject(gameObject);
	}

	public GameObject getCurrentGameObject() {
		return eventInfo.getCurrentGameObject();
	}

	public void finishNpcEvent() {
		if (eventInfo.getCurrentNpcEvent().getEventState() == EventStateEnum.OPENED) {
			eventInfo.getCurrentNpcEvent()
					.setEventState(EventStateEnum.CLEARED);
		}
	}

	public void finishGameObjectEvent() {
		if (eventInfo.getCurrentGameObjectEvent().getEventState() == EventStateEnum.OPENED) {
			eventInfo.getCurrentGameObjectEvent().setEventState(
					EventStateEnum.CLEARED);
		}
	}

	public void setCurrentEventInfo(EventPacket eventPacket) {
		eventInfo.setCurrentEventInfo(eventPacket);
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
		eventInfo.getCurrentEventInfo().setGreeting(isGreeting);
	}

	public boolean isGreeting() {
		return eventInfo.getCurrentEventInfo().isGreeting();
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

	public void triggerComponentEvent(int index) {
		String eventComponent = getCurrentEvent().getEventComponent()
				.get(index);
		if (getCurrentEvent().getEventTarget() != null) {
			NPC npc = eventAssets.getNpc(getCurrentEvent().getEventTarget());
			if (eventCheckManager.checkSameWithComponent(eventComponent, npc
					.getEvent(index + eventPlusRule).getEventName())) {
				setCurrentEventNpc(getCurrentEvent().getEventTarget());
				setCurrentEventNumber(index + eventPlusRule); // 알고리즘이필요함
				screenFactory.show(ScreenEnum.EVENT);
			}
		}
	}
}
