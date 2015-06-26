package com.mygdx.currentState;

import java.util.LinkedList;
import java.util.Queue;

public class RewardQueueInfo {
	// (5-1) 보상 달성 여부 큐
	private Queue<RewardInfo> rewardQueue = new LinkedList<RewardInfo>();

	// (5-2) 이미 달성한 이벤트 큐
	private Queue<RewardInfo> achievedRewardQueue = new LinkedList<RewardInfo>();

	public Queue<RewardInfo> getRewardQueue() {
		return rewardQueue;
	}

	public void setRewardQueue(Queue<RewardInfo> rewardQueue) {
		this.rewardQueue = rewardQueue;
	}

	public Queue<RewardInfo> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	public void setAchievedRewardQueue(Queue<RewardInfo> achievedRewardQueue) {
		this.achievedRewardQueue = achievedRewardQueue;
	}
}
