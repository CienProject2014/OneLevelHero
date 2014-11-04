package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.RewardTypeEnum;
import com.mygdx.event.RewardKey;

public class RewardManager {

	private Boolean currentReward;
	private RewardKey rewardKey;

	private static RewardManager instance;
	private String rewardCode;

	public RewardManager() {
		setRewardCode("none-none");
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
		rewardKey.setKeyOfRewardType(temp[0]);
		rewardKey.setKeyOfRewardValue(temp[1]);
		return rewardKey;
	}

	public RewardKey getRewardKey() {
		rewardCode = EventManager.getInstance().getEventKey().getKeyOfReward();

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

	public RewardManager setCurrentReward(Boolean currentReward) {
		this.currentReward = currentReward;
		return this;
	}

	public String getRewardCode() {
		return rewardCode;
	}

	public void setRewardCode(String rewardCode) {
		parseRewardCode(rewardCode);
		this.rewardCode = rewardCode;
	}

	public void doReward() {

		String rewardType = RewardManager.getInstance().rewardKey.getKeyOfRewardType();
		switch (RewardTypeEnum.valueOf(rewardType)) {
			case PARTY:
				RewardManager rewardManager = new PartyRewardManager();
				rewardManager.doReward();
				return;
			default:
				Gdx.app.log("error", "rewardType 주입 오류");
				return;
		}

	}

	public String getRewardMessage() {
		String rewardType = RewardManager.getInstance().rewardKey.getKeyOfRewardType();
		switch (RewardTypeEnum.valueOf(rewardType)) {
			case PARTY:
				RewardManager rewardManager = new PartyRewardManager();
				return rewardManager.getRewardMessage();

			default:
				Gdx.app.log("error", "rewardType 주입 오류");
				return null;
		}
	}

}
