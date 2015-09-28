package com.mygdx.manager;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.eventTrigger.EventTrigger;
import com.mygdx.factory.EventTriggerFactory;
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
	@Autowired
	private EventTriggerFactory eventTriggerFactory;

	public List<Reward> getEventRewards() {
		return rewardInfo.getEventRewardList();
	}

	public List<Reward> getAchievedRewards() {
		return rewardInfo.getAchievedRewardList();
	}

	public void addEventRewards(List<Reward> eventRewards) {
		if (eventRewards != null) {
			Iterator<Reward> rewardIterator = eventRewards.iterator();
			while (rewardIterator.hasNext()) {
				Reward reward = rewardIterator.next();
				if (reward.getRewardState() != EventStateEnum.ALWAYS_OPEN) {
					reward.setRewardState(EventStateEnum.ING);
				}
				getEventRewards().add(reward);
			}
		}
	}

	public void addSceneRewards(List<Reward> eventRewards) {
		if (eventRewards != null) {
			Iterator<Reward> rewardIterator = eventRewards.iterator();
			while (rewardIterator.hasNext()) {
				Reward reward = rewardIterator.next();
				if (reward.getRewardState() != EventStateEnum.ALWAYS_OPEN) {
					reward.setRewardState(EventStateEnum.ING);
				}
				rewardInfo.getSceneRewardList().add(reward);
			}
		}
	}

	public void doSceneRewards() {
		if (!rewardInfo.getSceneRewardList().isEmpty()) {
			Iterator<Reward> rewardIterator = rewardInfo.getSceneRewardList().iterator();
			for (; rewardIterator.hasNext();) {
				Reward peekedReward = rewardIterator.next();
				EventTrigger eventTrigger = eventTriggerFactory.getEventTrigger(peekedReward.getRewardType());
				eventTrigger.triggerEvent(peekedReward.getRewardParameter());
				if (!peekedReward.getRewardState().equals(EventStateEnum.ALWAYS_OPEN)) {
					peekedReward.setRewardState(EventStateEnum.CLEARED);
					rewardIterator.remove();
					getAchievedRewards().add(peekedReward);
				}
			}
		}
	}

	public void doEventRewards() {
		if (!rewardInfo.getEventRewardList().isEmpty()) {
			Iterator<Reward> rewardIterator = rewardInfo.getEventRewardList().iterator();
			for (; rewardIterator.hasNext();) {
				Reward peekedReward = rewardIterator.next();
				EventTrigger eventTrigger = eventTriggerFactory.getEventTrigger(peekedReward.getRewardType());
				eventTrigger.triggerEvent(peekedReward.getRewardParameter());
				if (!peekedReward.getRewardState().equals(EventStateEnum.ALWAYS_OPEN)) {
					peekedReward.setRewardState(EventStateEnum.CLEARED);
					rewardIterator.remove();
					getAchievedRewards().add(peekedReward);
				}
			}
		}
	}
}
