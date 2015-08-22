package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.event.Reward;

public class RewardInfo {
	// (5-1) 보상 달성 여부 큐
	private List<Reward> rewardList = new ArrayList<Reward>();

	// (5-2) 이미 달성한 이벤트 큐
	private List<Reward> achievedRewardList = new ArrayList<Reward>();

	public List<Reward> getRewardList() {
		return rewardList;
	}

	public void setRewardList(List<Reward> rewardList) {
		this.rewardList = rewardList;
	}

	public List<Reward> getAchievedRewardList() {
		return achievedRewardList;
	}

	public void setAchievedRewardList(List<Reward> achievedRewardList) {
		this.achievedRewardList = achievedRewardList;
	}

}
