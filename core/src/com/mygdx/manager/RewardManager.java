package com.mygdx.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.model.Reward;
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

	public List<Reward> getRewardList() {
		return rewardInfo.getRewardList();
	}

	public List<Reward> getAchievedRewardList() {
		return rewardInfo.getAchievedRewardList();
	}

	public void addEventReward(Reward reward) {
		if (reward.getRewardState() == RewardStateEnum.NOT_CLEARED) {
			reward.setRewardState(RewardStateEnum.ING);
		}
		getRewardList().add(reward);
	}

	// (1)에서 뺀후 (2)의 리스트에 집어넣는다.
	public void pollRewardQueue(Reward reward) {
		reward.setRewardState(RewardStateEnum.CLEARED);
		rewardInfo.getRewardList().remove(reward);
		getAchievedRewardList().add(reward);
	}

	public void doReward() {
		if (!getRewardList().isEmpty()) {
			for (Reward reward : getRewardList()) {
				switch (reward.getRewardType()) {
					case EVENT:
						eventManager.setCurrentEventPack(assets.npcMap
								.get(reward.getRewardTarget()), Integer
								.parseInt(reward.getRewardTargetAttribute()));
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
						partyInfo.addHero(assets.heroMap.get(reward
								.getRewardTarget()));
						return;
					default:
						return;
				}
			}
		}
	}

	public String getRewardMessage(Reward rewardInfo) {
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
