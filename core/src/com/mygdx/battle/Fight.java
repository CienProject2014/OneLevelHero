package com.mygdx.battle;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.OneLevelHero;
import com.mygdx.screen.VillageScreen;

public class Fight {

	OneLevelHero game;
	Random random = new Random();
	VillageScreen gameScreen;
	boolean outcome;
	public boolean battleEnd = false;

	public void decideOutcome() {
		outcome = random.nextBoolean();
		System.out.println(outcome);
		if (outcome)
			victory();
		else
			defeat();

	}

	public void victory() {
		Gdx.app.log("정보", "전투승리");
		battleEnd = true;
	}

	public void defeat() {
		Gdx.app.log("정보", "전투패배");
		battleEnd = true;
	}

}
