package com.mygdx.event;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.RewardTypeEnum;
import com.mygdx.manager.CurrentManager;

public class Reward {
	public void doReward(RewardTypeEnum rewardType, String rewardString) {
		switch (rewardType) {
			case PARTY:
				getPartyReward(rewardString);
				break;
			case GOLD:
				getGoldReward(rewardString);
				break;
			case EXPERIENCE:
				getExperienceReward(rewardString);
				break;
			case ITEM:
				getItemReward(rewardString);
				break;
			default:
				Gdx.app.log("Error", "RewardTypeError");
				break;
		}
	}

	private void getPartyReward(String rewardString) {
		CurrentManager.getInstance();
	}

	private void getGoldReward(String rewardString) {
		Gdx.app.log("INFO", "Reward : get GOLD!");
	}

	private void getExperienceReward(String rewardString) {
		Gdx.app.log("INFO", "Reward : get Experience!");
	}

	private void getItemReward(String rewardString) {
		Gdx.app.log("INFO", "Reward : get ITEM!");
	}

}
