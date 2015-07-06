package com.mygdx.manager;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.RewardPack;
import com.mygdx.state.Assets;

public class RewardManager {
	@Autowired
	private Assets assets;
	@Autowired
	private RewardInfo rewardInfo;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private ScreenFactory screenFactory;

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	// (3) 퀘스트 달성 여부 큐
	// 아직 미구현

	public Queue<RewardPack> getRewardQueue() {
		return rewardInfo.getRewardQueue();
	}

	public Queue<RewardPack> getAchievedRewardQueue() {
		return rewardInfo.getAchievedRewardQueue();
	}

	public void addEventReward(RewardPack rewardInfo) {
		if (rewardInfo.getRewardState() == RewardStateEnum.NOT_CLEARED) {
			rewardInfo.setRewardState(RewardStateEnum.ING);
		}
		getRewardQueue().add(rewardInfo);
	}

	// (1)에서 뺀후 (2)의 큐에 집어넣는다.
	public void pollRewardQueue() {
		getRewardQueue().peek().setRewardState(RewardStateEnum.CLEARED);
		getAchievedRewardQueue().add(getRewardQueue().poll());
	}

	public void doReward() {
		if (getRewardQueue().peek() != null) {
			RewardPack peekReward = getRewardQueue().peek();

			switch (peekReward.getRewardType()) {
				case EVENT:
					eventManager.setEventPack(assets.npcMap.get(peekReward
							.getRewardTarget()), Integer.parseInt(peekReward
							.getRewardTargetAttribute()));
					pollRewardQueue();
					screenFactory.show(ScreenEnum.EVENT);
					return;
				case EXPERIENCE:
					return;
				case GOLD:
					return;
				case ITEM:
					return;
				case NONE:
					return;
				case PARTY:
					partyInfo.addHero(assets.heroMap.get(getRewardQueue()
							.peek().getRewardTarget()));
					return;
				default:
					return;
			}
		}
	}

	public String getRewardMessage(RewardPack rewardInfo) {
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

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public PartyInfo getPartyInfo() {
		return partyInfo;
	}

	public void setPartyInfo(PartyInfo partyInfo) {
		this.partyInfo = partyInfo;
	}

	public RewardInfo getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(RewardInfo rewardInfo) {
		this.rewardInfo = rewardInfo;
	}
}
