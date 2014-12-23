package com.mygdx.manager;

import java.util.Queue;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.RewardInfo;

public class RewardManager {

	public Queue<RewardInfo> getRewardQueue() {
		return rewardQueue;
	}

	public Queue<RewardInfo> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	private Queue<RewardInfo> rewardQueue;
	private Queue<RewardInfo> achievedRewardQueue;
	// (3) 퀘스트 달성 여부 큐

	// 아직 미구현
	private static RewardManager instance;

	public static RewardManager getInstance() {
		if (null == instance) {
			instance = new RewardManager();
		}
		return instance;
	}

	public RewardManager() {
		rewardQueue = CurrentManager.getInstance().getRewardQueue();
		achievedRewardQueue = CurrentManager.getInstance()
				.getAchievedRewardQueue();
	}

	public void addEventReward(RewardInfo rewardInfo) {
		rewardInfo.setRewardState(RewardStateEnum.ING);
		rewardQueue.add(rewardInfo);
	}

	// (1)에서 뺀후 (2)의 큐에 집어넣는다.
	public void pollRewardQueue() {
		rewardQueue.peek().setRewardState(RewardStateEnum.CLEARED);
		achievedRewardQueue.add(rewardQueue.poll());
	}

	public String getRewardMessage(RewardInfo rewardInfo) {
		switch (rewardInfo.getRewardType()) {
			case EXPERIENCE:
				return rewardInfo.getRewardTarget() + "의 경험치를 획득했습니다.";
			case GOLD:
				return rewardInfo.getRewardTarget() + "의 골드를 획득했습니다.";
			case ITEM:
				return rewardInfo.getRewardTarget() + "아이템을 획득했습니다.";
			case NONE:
				return "보상 없음";
			case PARTY:
				return rewardInfo.getRewardTarget() + "이 파티에 합류하였습니다.";
			default:
				return "보상 없음";
		}
	}
}
