package com.mygdx.manager;

import java.util.LinkedList;
import java.util.Queue;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.Reward;

public class RewardInfo {
	// (1) 보상 달성 여부 큐
	private Queue<Reward> rewardQueue;

	// (2) 이미 달성한 이벤트 큐
	private Queue<Reward> achievedRewardQueue;

	// (3) 퀘스트 달성 여부 큐

	// 아직 미구현
	public RewardInfo() {
		rewardQueue = new LinkedList<Reward>();
		achievedRewardQueue = new LinkedList<Reward>();
	}

	public void addEventReward(Reward reward) {
		reward.setRewardState(RewardStateEnum.ING);
		rewardQueue.add(reward);
	}

	// (1)에서 뺀후 (2)의 큐에 집어넣는다.
	public void removeEventReward() {
		rewardQueue.peek().setRewardState(RewardStateEnum.CLEARED);
		achievedRewardQueue.add(rewardQueue.poll());
	}

	public Queue<Reward> getRewardQueue() {
		return rewardQueue;
	}

	public Queue<Reward> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

}
