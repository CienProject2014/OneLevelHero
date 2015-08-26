package com.mygdx.manager;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.model.event.Reward;

public class RewardManager {
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private BagManager bagManager;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private RewardInfo rewardInfo;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private FieldManager fieldManager;

	// (3) 퀘스트 달성 여부 큐
	// 아직 미구현

	public List<Reward> getRewards() {
		return rewardInfo.getRewardList();
	}

	public List<Reward> getAchievedRewards() {
		return rewardInfo.getAchievedRewardList();
	}

	public void addEventReward(Reward rewardInfo) {
		if (rewardInfo.getRewardState() != RewardStateEnum.ALWAYS_OPEN) {
			rewardInfo.setRewardState(RewardStateEnum.ING);
		}
		getRewards().add(rewardInfo);
	}

	public void doRewards() {
		if (!getRewards().isEmpty()) {
			Iterator<Reward> rewardIterator = getRewards().iterator();
			for (; rewardIterator.hasNext();) {
				Reward peekedReward = rewardIterator.next();
				switch (peekedReward.getRewardType()) {
					case EXPERIENCE :
						break;
					case GOLD :
						break;
					case ITEM_CLOTHES :
						bagManager.possessItem(ItemEnum.CLOTHES, peekedReward.getRewardComponent().get(0));
						break;
					case ITEM_HANDGRIP :
						bagManager.possessItem(ItemEnum.HANDGRIP, peekedReward.getRewardComponent().get(0));
						break;
					case NONE :
						break;
					case REST_IN_NODE :
						Gdx.app.log("RewardManager", "Rest in Node");
						partyManager.setFatigue(0);
						partyManager.healAllHero();
						break;
					case PASS_TIME :
						timeManager.plusMinute(Integer.parseInt(peekedReward.getRewardComponent().get(0)));
						break;
					case MOVE_NODE :
						Gdx.app.log("RewardManager", "Move node - " + peekedReward.getRewardComponent().get(0));
						positionManager.setCurrentNodeName(peekedReward.getRewardComponent().get(0));
						movingManager.goCurrentPosition();
						break;
					case NEXT_SECTION :
						storySectionManager.setNewStorySectionAndPlay(Integer.valueOf(peekedReward.getRewardComponent()
								.get(0)));
						break;
					case SAVE :
						saveManager.save();
						break;
					case JOIN_PARTY :
						partyManager.addHero(unitAssets.getHero(peekedReward.getRewardComponent().get(0)));
						break;
					default :
						break;
				}
				if (!peekedReward.getRewardState().equals(RewardStateEnum.ALWAYS_OPEN)) {
					peekedReward.setRewardState(RewardStateEnum.CLEARED);
					rewardIterator.remove();
					getAchievedRewards().add(peekedReward);
				}
			}
		}
	}
	public String getRewardMessage(Reward rewardInfo) {
		switch (rewardInfo.getRewardType()) {
			case EXPERIENCE :
				return rewardInfo.getRewardComponent().get(0) + "의 경험치를 획득했습니다.";
			case GOLD :
				return rewardInfo.getRewardComponent().get(0) + "의 골드를 획득했습니다.";
			case ITEM :
				return rewardInfo.getRewardComponent().get(0) + "아이템을 획득했습니다.";
			case NONE :
				return "보상 없음";
			case JOIN_PARTY :
				return rewardInfo.getRewardComponent().get(0) + "이 파티에 합류하였습니다.";
			default :
				return "보상 없음";
		}
	}

}
