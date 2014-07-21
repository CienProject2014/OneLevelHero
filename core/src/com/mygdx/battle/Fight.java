package com.mygdx.battle;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.mygdx.game.OneLevelHero;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.GameScreen;
import com.mygdx.resource.Assets;

public class Fight{
	
	
	OneLevelHero game;
	Random random = new Random();
	GameScreen gameScreen;
	boolean outcome;
	public boolean battleEnd = false;
	
	public void decideOutcome(){
		outcome = random.nextBoolean();
		System.out.println(outcome);
		if (outcome)
			victory();
		else
			defeat();
		
	}
	
	public void victory(){
		Gdx.app.log("정보","전투승리");
		battleEnd = true;
	}
	
	public void defeat(){
		Gdx.app.log("정보","전투패배");
		battleEnd = true;
	}
	
}

	
