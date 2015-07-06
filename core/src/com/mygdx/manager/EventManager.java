package com.mygdx.manager;

import java.util.Iterator;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.currentState.EventInfo;
import com.mygdx.factory.StageFactory;
import com.mygdx.model.EventPack;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;

/**
 * CHAT, SELECT 등의 이벤트정보를 세팅해주는 클래스 CHAT 이벤트의 경우 Iterator를 돌려서 EventScene을 CHAT이벤트가 끝날때까지 리턴해준다.
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
	private StageFactory stageFactory;
	private static Iterator<EventScene> eventSceneIterator;

	public Stage getEvent() {
		Queue<EventPack> eventQueue = eventInfo.getEventQueue();
		while (!eventQueue.isEmpty()) {
			EventPack eventPack = eventQueue.poll();
			switch (eventPack.getEventType()) {
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
					Gdx.app.log("EventManager", "EventTypeEnum 주입 오류");
					return null;
			}
		}
		return null;
	}

	private Stage doSelectComponentEvent(EventInfo eventInfo) {
		// TODO Auto-generated method stub
		return null;
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

		eventSceneIterator = eventInfo.getEventQueue().poll().getCurrentEvent()
				.getEventSceneIterator();
		// 리워드를 eventRewardQueue에 추가
		//addEventRewardQueue(npc);
		return eventSceneIterator;
	}

	private void addEventRewardQueue(NPC npc) {
		//RewardInfo rewardInfo = eventQueueInfo.getCurrentEvent().getReward();
		//if (rewardInfo != null)
		//	if (rewardInfo.getRewardState() == RewardStateEnum.NOT_CLEARED
		//			|| rewardInfo.getRewardState() == RewardStateEnum.NO_ISSUE)
		//		rewardManager.addEventReward(rewardInfo);
	}

	public void finishEvent() {
		//eventQueueInfo.getCurrentEvent().setEventState(EventStateEnum.CLEARED);
	}

	public void setEventPack(NPC npc, int eventNumber, boolean isGreeting) {
		EventPack currentEventInfo = new EventPack();
		currentEventInfo.setCurrentNpc(npc);
		currentEventInfo.setCurrentEvent(npc.getEvent(eventNumber));
		currentEventInfo.setGreeting(isGreeting);
		eventInfo.getEventQueue().add(currentEventInfo);
	}

	public void setEventPack(NPC npc, int eventNumber) {
		setEventPack(npc, eventNumber, false);
	}

	public void setEventPack(NPC npc, boolean isGreeting) {
		setEventPack(npc, 0, isGreeting);
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
}
