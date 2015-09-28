package com.mygdx.model.event;

import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;

public class Reward {
	private EventTypeEnum rewardType;
	private EventStateEnum rewardState;
	private EventParameters rewardParameter;
	private String rewardTitle;
	public EventTypeEnum getRewardType() {
		return rewardType;
	}
	public void setRewardType(EventTypeEnum rewardType) {
		this.rewardType = rewardType;
	}
	public EventStateEnum getRewardState() {
		return rewardState;
	}
	public void setRewardState(EventStateEnum rewardState) {
		this.rewardState = rewardState;
	}
	public EventParameters getRewardParameter() {
		return rewardParameter;
	}
	public void setRewardParameter(EventParameters rewardParameter) {
		this.rewardParameter = rewardParameter;
	}
	public String getRewardTitle() {
		return rewardTitle;
	}
	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}

}
