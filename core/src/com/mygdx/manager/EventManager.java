package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.RewardStateEnum;
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
	private static Iterator<EventScene> eventSceneIterator;

	public Iterator<EventScene> getEventSceneIterator() {
		NPC npc = eventInfo.getNpc();
		eventSceneIterator = npc.getEvent(eventInfo.getEventNumber())
				.getEventSceneIterator();

		// 리워드를 eventRewardQueue에 추가
		addEventRewardQueue(npc);

		return eventSceneIterator;
	}

	private void addEventRewardQueue(NPC npc) {
		RewardInfo rewardInfo = npc.getEvent(eventInfo.getEventNumber())
				.getReward();
		if (rewardInfo != null)
			if (rewardInfo.getRewardState() == RewardStateEnum.NOT_CLEARED)
				rewardManager.addEventReward(rewardInfo);
	}

	public void finishEvent() {
		eventInfo.getNpc().getEvent(eventInfo.getEventNumber())
				.setEventState(EventStateEnum.CLEARED);
	}

	public void setEventInfo(NPC npc, int eventNumber, boolean isGreeting) {
		eventInfo.setNpc(npc);
		eventInfo.setEventNumber(eventNumber);
		eventInfo.setGreeting(isGreeting);
	}

	public void setEventInfo(NPC npc, int eventNumber) {
		setEventInfo(npc, eventNumber, false);
	}

	public void setEventInfo(NPC npc, boolean isGreeting) {
		setEventInfo(npc, 0, isGreeting);
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public RewardManager getRewardManager() {
		return rewardManager;
	}

	public void setRewardManager(RewardManager rewardManager) {
		this.rewardManager = rewardManager;
	}
}
