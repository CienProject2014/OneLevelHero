package com.mygdx.event;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.RewardTypeEnum;

public class RewardManager {

	private Boolean currentReward;
	private RewardKey rewardKey;

	private static RewardManager instance;
	private String rewardCode;

	public RewardManager() {
		setRewardCode("none");
		setCurrentReward(false);
	}

	public static RewardManager getInstance() {
		if (null == instance) {
			instance = new RewardManager();
		}
		return instance;
	}

	private RewardKey parseRewardCode(String rewardCode) {
		if (rewardCode == null) {
			Gdx.app.log("error", "rewardCode is null");
		}

		String[] temp = rewardCode.split("-");
		rewardKey = new RewardKey();
		if (temp[0].equals("party")) {
			rewardKey.setKeyOfRewardType(RewardTypeEnum.PARTY);
		} else if (temp[0].equals("gold")) {
			rewardKey.setKeyOfRewardType(RewardTypeEnum.GOLD);
		} else if (temp[0].equals("item")) {
			rewardKey.setKeyOfRewardType(RewardTypeEnum.ITEM);
		} else {
			Gdx.app.log("error", "rewardType 주입 에러");
		}
		rewardKey.setKeyOfRewardValue(temp[1]);
		return rewardKey;
	}

	public RewardKey getRewardKey() {
		parseRewardCode(rewardCode);
		return rewardKey;
	}

	public RewardKey getRewardKey(String rewardCode) {
		parseRewardCode(rewardCode);
		return rewardKey;
	}

	public void setRewardKey(RewardKey rewardKey) {
		this.rewardKey = rewardKey;
	}

	public Boolean isCurrentReward() {
		return currentReward;
	}

	public void setCurrentReward(Boolean currentReward) {
		this.currentReward = currentReward;
	}

	public String getRewardCode() {
		return rewardCode;
	}

	public void setRewardCode(String rewardCode) {
		this.rewardCode = rewardCode;
	}

}
