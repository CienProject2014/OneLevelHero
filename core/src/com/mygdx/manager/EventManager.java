package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.model.Event;
import com.mygdx.model.EventPacket;
import com.mygdx.model.EventScene;
import com.mygdx.model.GameObject;
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

	private Iterator<EventScene> eventSceneIterator;

	public Stage getEvent() {
		Event currentEvent = eventInfo.getCurrentEvent();
		switch (currentEvent.getEventType()) {
			case CHAT:
			case CREDIT:
			case SELECT_COMPONENT:
			case SELECT_EVENT:
				return getChatScene();
			case MOVE:
			default:
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

	public Iterator<EventScene> getEventSceneIterator() {
		eventSceneIterator = eventInfo.getCurrentEvent()
				.getEventSceneIterator();
		addEventRewardQueue(eventInfo.getCurrentEvent().getReward());

		return eventSceneIterator;
	}

	private void addEventRewardQueue(RewardInfo rewardPacket) {
		if (rewardPacket != null)
			if (rewardPacket.getRewardState() == RewardStateEnum.NOT_CLEARED)
				rewardManager.addEventReward(rewardPacket);
	}

	public NPC getCurrentNpc() {
		return eventInfo.getCurrentNpc();
	}

	public Event getCurrentEvent() {
		return eventInfo.getCurrentEvent();
	}

	public void setCurrentGameObject(GameObject gameObject) {
		eventInfo.setCurrentGameObject(gameObject);
	}

	public GameObject getCurrentGameObject() {
		return eventInfo.getCurrentGameObject();
	}

	public void finishEvent() {
		eventInfo.getCurrentEvent().setEventState(EventStateEnum.CLEARED);
	}

	public void setCurrentEventInfo(EventPacket eventPacket) {
		eventInfo.setCurrentEventInfo(eventPacket);
	}

	public void setCurrentEventNumber(int eventNumber) {
		eventInfo.setCurrentEventNumber(eventNumber);
	}

	public void setCurrentEventNpc(String npcName) {
		eventInfo.setCurrentEventNpc(npcName);
	}
}
