package com.mygdx.currentState;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.event.Reward;

public class RewardInfo {
	// (5-1) 보상 달성 여부 큐
	private List<Reward> eventRewardList = new ArrayList<Reward>();
	private List<Reward> sceneRewardList = new ArrayList<Reward>();
	// (5-2) 이미 달성한 이벤트 큐
	private List<Reward> achievedRewardList = new ArrayList<Reward>();

	public List<Reward> getEventRewardList() {
		return eventRewardList;
	}

	public void setEventRewardList(List<Reward> eventRewardList) {
		this.eventRewardList = eventRewardList;
	}

	public List<Reward> getSceneRewardList() {
		return sceneRewardList;
	}

	public void setSceneRewardList(List<Reward> sceneRewardList) {
		this.sceneRewardList = sceneRewardList;
	}

	public List<Reward> getAchievedRewardList() {
		return achievedRewardList;
	}

	public void setAchievedRewardList(List<Reward> achievedRewardList) {
		this.achievedRewardList = achievedRewardList;
	}

}
