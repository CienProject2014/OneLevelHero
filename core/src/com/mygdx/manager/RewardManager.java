package com.mygdx.manager;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.currentState.RewardQueueInfo;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.state.Assets;

@Component
public class RewardManager {
	@Autowired
	private RewardQueueInfo rewardQueueInfo;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PartyInfo partyInfo;

	// (3) 퀘스트 달성 여부 큐
	// 아직 미구현

	public Queue<RewardInfo> getRewardQueue() {
		return rewardQueueInfo.getRewardQueue();
	}

	public Queue<RewardInfo> getAchievedRewardQueue() {
		return rewardQueueInfo.getAchievedRewardQueue();
	}

	public void addEventReward(RewardInfo rewardInfo) {
		rewardInfo.setRewardState(RewardStateEnum.ING);
		getRewardQueue().add(rewardInfo);
	}

	// (1)에서 뺀후 (2)의 큐에 집어넣는다.
	public void pollRewardQueue() {
		getRewardQueue().peek().setRewardState(RewardStateEnum.CLEARED);
		getAchievedRewardQueue().add(getRewardQueue().poll());
	}

	public void doReward() {
		if (getRewardQueue().peek() != null) {
			switch (getRewardQueue().peek().getRewardType()) {
				case EXPERIENCE:
					return;
				case GOLD:
					return;
				case ITEM:
					return;
				case NONE:
					return;
				case PARTY:
					partyInfo.addHero(Assets.heroMap.get(getRewardQueue()
							.peek().getRewardTarget()));
					return;
				default:
					return;
			}
		}
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
