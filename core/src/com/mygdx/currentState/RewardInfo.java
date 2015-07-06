package com.mygdx.currentState;

import java.util.LinkedList;
import java.util.Queue;

import com.mygdx.model.RewardPack;

public class RewardInfo {
	// 보상 달성 여부 큐
	private Queue<RewardPack> rewardQueue = new LinkedList<RewardPack>();
	// 이미 달성한 보상 큐
	private Queue<RewardPack> achievedRewardQueue = new LinkedList<RewardPack>();

	public Queue<RewardPack> getRewardQueue() {
		return rewardQueue;
	}

	public void setRewardQueue(Queue<RewardPack> rewardQueue) {
		this.rewardQueue = rewardQueue;
	}

	public Queue<RewardPack> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	public void setAchievedRewardQueue(Queue<RewardPack> achievedRewardQueue) {
		this.achievedRewardQueue = achievedRewardQueue;
	}
}
