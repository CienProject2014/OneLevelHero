package com.mygdx.manager;

import java.util.LinkedList;
import java.util.Queue;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.Reward;

public class RewardInfo {
	// (1) 보상 달성 여부 큐
	private Queue<Reward> eventRewardQueue;

	// (2) 퀘스트 달성 여부 큐

	public RewardInfo() {
		eventRewardQueue = new LinkedList<Reward>();
	}

	public void addEventReward(Reward reward) {

		reward.setRewardState(RewardStateEnum.ING);
		eventRewardQueue.add(reward);
	}

	public void removeEventReward(Reward reward) {
		reward.setRewardState(RewardStateEnum.CLEARED);
		eventRewardQueue.remove(reward);
	}

	public Queue<Reward> getEventRewardQueue() {
		return eventRewardQueue;
	}

	public void setEventRewardQueue(Queue<Reward> eventRewardQueue) {
		this.eventRewardQueue = eventRewardQueue;
	}

}
