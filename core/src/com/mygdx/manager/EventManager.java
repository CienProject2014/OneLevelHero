package com.mygdx.manager;

import java.util.Iterator;

import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;

public class EventManager {
	private static EventInfo eventInfo = new EventInfo();
	private static Iterator<EventScene> iterator;

	private EventManager() {
	}

	public static Iterator<EventScene> getEventIterator() {
		final NPC npc = getEventInfo().getNpc();
		iterator = getEventSceneIterator(npc, eventInfo.getEventNumber());

		//리워드를 eventRewardQueue에 추가
		addEventRewardQueue(npc);

		return iterator;
	}

	private static void addEventRewardQueue(NPC npc) {
		RewardInfo rewardInfo = npc.getEvent(eventInfo.getEventNumber())
				.getReward();
		if (rewardInfo != null) {
			if (rewardInfo.getRewardState() == RewardStateEnum.NOT_CLEARED) {
				RewardManager.addEventReward(rewardInfo);
			}
		}
	}

	public static EventInfo getEventInfo() {
		return eventInfo;
	}

	public static void endEvent() {
		eventInfo.getNpc().getEvent(eventInfo.getEventNumber())
				.setEventState(EventStateEnum.CLEARED);
	}

	public static void setEventInfo(NPC npc, int eventNumber, boolean isGreeting) {
		eventInfo.setNpc(npc);
		eventInfo.setEventNumber(eventNumber);
		eventInfo.setGreeting(isGreeting);
	}

	public static void setEventInfo(NPC npc, int eventNumber) {
		setEventInfo(npc, eventNumber, false);
	}

	public static void setEventInfo(NPC npc, boolean isGreeting) {
		setEventInfo(npc, 0, isGreeting);
	}

	private static Iterator<EventScene> getEventSceneIterator(NPC npc,
			int eventNo) {

		return npc.getEvent(eventNo).getEventSceneIterator();

	}

}
