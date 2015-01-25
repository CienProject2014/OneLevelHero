package com.mygdx.manager;

import java.util.Queue;

import com.mygdx.currentState.CurrentState;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.state.Assets;

public class RewardManager {

	private static Queue<RewardInfo> rewardQueue = CurrentState.getInstance()
			.getRewardQueue();;
	private static Queue<RewardInfo> achievedRewardQueue = CurrentState
			.getInstance().getAchievedRewardQueue();;

	// (3) 퀘스트 달성 여부 큐
	// 아직 미구현

	public static Queue<RewardInfo> getRewardQueue() {
		return rewardQueue;
	}

	public static void setRewardQueue(Queue<RewardInfo> rewardQueue) {
		RewardManager.rewardQueue = rewardQueue;
	}

	public static Queue<RewardInfo> getAchievedRewardQueue() {
		return achievedRewardQueue;
	}

	public static void setAchievedRewardQueue(
			Queue<RewardInfo> achievedRewardQueue) {
		RewardManager.achievedRewardQueue = achievedRewardQueue;
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

	private static EventInfo getEventInfo() {
		return EventManager.getEventInfo();
	}

	private static RewardInfo getRewardInfo() {
		RewardInfo rewardInfo = getEventInfo().getNpc()
				.getEvent(getEventInfo().getEventNumber()).getReward();
		return rewardInfo;
	}

	public static void doReward() {
		RewardInfo rewardInfo = getRewardInfo();
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
					PartyInfo party = CurrentState.getInstance().getParty();
					party.addHero(Assets.heroMap.get(rewardQueue.peek()
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
