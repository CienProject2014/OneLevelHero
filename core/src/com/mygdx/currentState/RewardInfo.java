package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mygdx.model.Reward;

public class RewardInfo {
	// 보상 달성 여부 리스트
	private List<Reward> rewardList = new LinkedList<>();
	// 이미 달성한 보상 리스트
	private List<Reward> achievedRewardList = new ArrayList<>();

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
