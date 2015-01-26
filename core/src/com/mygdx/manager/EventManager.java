package com.mygdx.manager;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;

@Component
public class EventManager {
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private RewardManager rewardManager;
	private Iterator<EventScene> iterator;

	private EventManager() {
	}

	public Iterator<EventScene> getEventIterator() {
		final NPC npc = getEventInfo().getNpc();
		iterator = getEventSceneIterator(npc, eventInfo.getEventNumber());

		//리워드를 eventRewardQueue에 추가
		addEventRewardQueue(npc);

		return iterator;
	}

	private void addEventRewardQueue(NPC npc) {
		RewardInfo rewardInfo = npc.getEvent(eventInfo.getEventNumber())
				.getReward();
		if (rewardInfo != null) {
			if (rewardInfo.getRewardState() == RewardStateEnum.NOT_CLEARED) {
				rewardManager.addEventReward(rewardInfo);
			}
		}
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void endEvent() {
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

	private Iterator<EventScene> getEventSceneIterator(NPC npc, int eventNo) {

		return npc.getEvent(eventNo).getEventSceneIterator();

	}

}
