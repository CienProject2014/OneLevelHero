package com.mygdx.manager;

import java.util.Iterator;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.EventInfo;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;
import com.mygdx.model.RewardInfo;

public class EventManager {
	private static EventManager instance;
	private static EventInfo eventInfo;
	private static Iterator<EventScene> iterator;
	private static RewardManager rewardManager = RewardManager.getInstance();

	private EventManager() {
		eventInfo = new EventInfo();

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
				rewardManager.addEventReward(rewardInfo);
			}
		}
	}

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

	public static EventInfo getEventInfo() {
		return eventInfo;
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

	private static Iterator<EventScene> getEventSceneIterator(NPC npc,
			int eventNo) {

		return npc.getEvent(eventNo).getEventSceneIterator();

	}

}
