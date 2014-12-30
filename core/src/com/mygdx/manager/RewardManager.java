package com.mygdx.manager;

import java.util.Queue;

import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.EventInfo;
import com.mygdx.model.Party;
import com.mygdx.model.RewardInfo;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class RewardManager {

	public Queue<RewardInfo> getRewardQueue() {
		return rewardQueue;
	}

	public Queue<RewardInfo> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	private static Queue<RewardInfo> rewardQueue;
	private static Queue<RewardInfo> achievedRewardQueue;
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
		rewardQueue = CurrentState.getInstance().getRewardQueue();
		achievedRewardQueue = CurrentState.getInstance()
				.getAchievedRewardQueue();
	}

	public static void addEventReward(RewardInfo rewardInfo) {
		rewardInfo.setRewardState(RewardStateEnum.ING);
		rewardQueue.add(rewardInfo);
	}

	// (1)에서 뺀후 (2)의 큐에 집어넣는다.
	public static void pollRewardQueue() {
		rewardQueue.peek().setRewardState(RewardStateEnum.CLEARED);
		achievedRewardQueue.add(rewardQueue.poll());
	}

	public static void doReward() {
		EventInfo eventInfo = EventManager.getEventInfo();
		RewardInfo rewardInfo = eventInfo.getNpc()
				.getEvent(eventInfo.getEventNumber()).getReward();
		if (rewardInfo != null) {
			switch (rewardQueue.peek().getRewardType()) {
				case EXPERIENCE:
					return;
				case GOLD:
					return;
				case ITEM:
					return;
				case NONE:
					return;
				case PARTY:
					Party party = CurrentState.getInstance().getParty();
					party.addParty(Assets.heroMap.get(rewardQueue.peek()
							.getRewardTarget()));
					return;
				default:
					return;
			}
		}
	}

	public static String getRewardMessage(RewardInfo rewardInfo) {
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
