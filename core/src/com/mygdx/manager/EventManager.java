package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.model.Event;
import com.mygdx.model.EventPacket;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;
import com.mygdx.state.Assets;

/**
 * CHAT, SELECT 등의 이벤트정보를 세팅해주는 클래스 CHAT 이벤트의 경우 Iterator를 돌려서 EventScene을 CHAT이벤트가 끝날때까지 리턴해준다.
 *
 * @author Velmont
 *
 */
public class EventManager {
	@Autowired
	private Assets assets;
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private StageFactory stageFactory;

	public Stage getEvent() {
		Event currentEvent = eventInfo.getCurrentEvent();
		switch (currentEvent.getEventType()) {
			case CHAT:
				return getChatEvent(eventInfo);
			case MOVE:
				return doMoveEvent(eventInfo);
			case CREDIT:
				return doCreditEventeventInfo();
			case SELECT_COMPONENT:
				return doSelectComponentEvent(eventInfo);
			case SELECT_EVENT:
				return doMoveEvent(eventInfo);
			default:
				Gdx.app.error("EventManager", "EventTypeEnum 정보가 없습니다.");
				throw new NullPointerException();
		}
	}

	private Stage doSelectComponentEvent(EventInfo eventInfo) {
		Iterator<EventScene> eventSceneIterator = getEventSceneIterator(eventInfo);
		if (eventSceneIterator.hasNext()) {
			Stage eventStage = stageFactory.makeEventStage(eventSceneIterator);
			return eventStage;
		} else {
			Gdx.app.error("EventManager", "EventInfo의 SceneIterator 정보가 없습니다.");
			throw new NullPointerException();
		}
	}

	private Stage doCreditEventeventInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	private Stage doMoveEvent(EventInfo eventPack) {
		// TODO Auto-generated method stub
		return null;
	}

	private Stage getChatEvent(EventInfo eventInfo) {
		Iterator<EventScene> eventSceneIterator = getEventSceneIterator(eventInfo);
		if (eventSceneIterator.hasNext()) {
			Stage eventStage = stageFactory.makeEventStage(eventSceneIterator);
			return eventStage;
		} else {
			Gdx.app.log("EventManager", "EventInfo - SceneIterator 주입 에러");
			return null;
		}

	}

	public Iterator<EventScene> getEventSceneIterator(EventInfo eventInfo) {
		Iterator<EventScene> eventSceneIterator = eventInfo.getCurrentEvent()
				.getEventSceneIterator();
		// 리워드를 eventRewardQueue에 추가
		//addEventRewardQueue(npc);
		return eventSceneIterator;
	}

	private void addEventRewardQueue(NPC npc) {
	}

	public void finishEvent() {
	}

	public void addEventQueue(NPC npc, int eventNumber, boolean isGreeting) {
		Event event = assets.npcMap.get(npc.getName()).getEvent(eventNumber);
		eventInfo.getEventQueue().add(event);
	}

	public void addEventQueue(Event event) {
		eventInfo.getEventQueue().add(event);
	}

	public void setCurrentEventPack(NPC npc, int eventNumber) {
		addEventQueue(npc, eventNumber, false);
	}

	public void setEventPack(NPC npc, boolean isGreeting) {
		addEventQueue(npc, 0, isGreeting);
	}

	public RewardManager getRewardManager() {
		return rewardManager;
	}

	public void setRewardManager(RewardManager rewardManager) {
		this.rewardManager = rewardManager;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public StageFactory getStageFactory() {
		return stageFactory;
	}

	public void setStageFactory(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

	public void setCurrentEventPack(EventPacket eventPack) {

	}

	//eventQueue에 event를 주입하고 상태를 OPEN한다.
	public void injectEvent(EventPacket eventPack) {
		Event injectedEvent = assets.npcMap.get(eventPack.getEventNpc())
				.getEvent(eventPack.getEventNumber());
		injectedEvent.setEventState(EventStateEnum.OPENED);
		eventInfo.getEventQueue().add(injectedEvent);
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}
}
