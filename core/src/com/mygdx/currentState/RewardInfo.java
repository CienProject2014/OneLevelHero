package com.mygdx.currentState;

import java.util.LinkedList;
import java.util.Queue;

import com.mygdx.model.event.Reward;

public class RewardInfo {
	// (5-1) 보상 달성 여부 큐
	private Queue<Reward> rewardQueue = new LinkedList<Reward>();

	// (5-2) 이미 달성한 이벤트 큐
	private Queue<Reward> achievedRewardQueue = new LinkedList<Reward>();

	public Queue<Reward> getRewardQueue() {
		return rewardQueue;
	}

	public void setRewardQueue(Queue<Reward> rewardQueue) {
		this.rewardQueue = rewardQueue;
	}

	public Queue<Reward> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	public void setAchievedRewardQueue(Queue<Reward> achievedRewardQueue) {
		this.achievedRewardQueue = achievedRewardQueue;
	}
}
