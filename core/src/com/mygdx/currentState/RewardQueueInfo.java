package com.mygdx.currentState;

import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class RewardQueueInfo {

	@PostConstruct
	public void init() {
		setRewardQueue(new LinkedList<RewardInfo>());
		setAchievedRewardQueue(new LinkedList<RewardInfo>());
	}

	// (5-1) 보상 달성 여부 큐
	private Queue<RewardInfo> rewardQueue;

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

	// (5-2) 이미 달성한 이벤트 큐
	private Queue<RewardInfo> achievedRewardQueue;
}
