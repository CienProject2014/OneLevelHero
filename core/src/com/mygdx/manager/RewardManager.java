package com.mygdx.manager;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.event.Reward;
import com.mygdx.store.Loadable;
import com.mygdx.store.Savable;

public class RewardManager implements Savable<RewardInfo>, Loadable<RewardInfo> {
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private StorySectionManager storySectionManager;

	private RewardInfo rewardInfo = new RewardInfo();

	// (3) 퀘스트 달성 여부 큐
	// 아직 미구현

	public Queue<Reward> getRewardQueue() {
		return rewardInfo.getRewardQueue();
	}

	public Queue<Reward> getAchievedRewardQueue() {
		return rewardInfo.getAchievedRewardQueue();
	}

	public void addEventReward(Reward rewardInfo) {
		rewardInfo.setRewardState(RewardStateEnum.ING);
		getRewardQueue().add(rewardInfo);
	}

	// (1)에서 뺀후 (2)의 큐에 집어넣는다.
	public void pollRewardQueue() {
		getRewardQueue().peek().setRewardState(RewardStateEnum.CLEARED);
		getAchievedRewardQueue().add(getRewardQueue().poll());
	}

	public void doReward() {
		if (!getRewardQueue().isEmpty()) {
			while (getRewardQueue().iterator().hasNext()) {
				Reward peekedReward = getRewardQueue().poll();
				switch (peekedReward.getRewardType()) {
					case EXPERIENCE :
						break;
					case GOLD :
						break;
					case ITEM :
						break;
					case NONE :
						break;
					case REST_IN_NODE :
						Gdx.app.log("RewardManager", "Rest in Node");
						partyManager.setFatigue(0);
						partyManager.healAllHero();
						break;
					case PASS_TIME :
						timeManager.plusMinute(Integer.parseInt(peekedReward
								.getRewardTargetAttribute()));
						break;
					case NEXT_SECTION :
						storySectionManager.setNewStorySectionAndPlay(Integer
								.valueOf(peekedReward
										.getRewardTargetAttribute()));
						break;
					case PARTY :
						partyManager.addHero(unitAssets.getHero(peekedReward
								.getRewardTarget()));
						break;
					default :
						break;
				}
			}
		}
	}

	public String getRewardMessage(Reward rewardInfo) {
		switch (rewardInfo.getRewardType()) {
			case EXPERIENCE :
				return rewardInfo.getRewardTarget() + "의 경험치를 획득했습니다.";
			case GOLD :
				return rewardInfo.getRewardTarget() + "의 골드를 획득했습니다.";
			case ITEM :
				return rewardInfo.getRewardTarget() + "아이템을 획득했습니다.";
			case NONE :
				return "보상 없음";
			case PARTY :
				return rewardInfo.getRewardTarget() + "이 파티에 합류하였습니다.";
			default :
				return "보상 없음";
		}
	}

	@Override
	public void setData(RewardInfo rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

	@Override
	public RewardInfo getData() {
		return rewardInfo;
	}
}
