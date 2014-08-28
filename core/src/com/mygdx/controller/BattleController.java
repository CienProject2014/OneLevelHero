package com.mygdx.controller;

import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;
import com.mygdx.util.CurrentManager;

public class BattleController {
	Random random;
	JSONParser parser = new JSONParser();
	JSONObject villages;
	String key1, key2, key3;
	String delimiter = "-";
	
	public BattleController() {
		random = new Random();
	}
	
	// 출발지와 도착지의 정보를 받아서 해당하는 던전을 CurrentDungeon으로 설정.
	void setDungeon() {
		JSONObject setting = (JSONObject) Assets.dungeon_json.get("setting");
		JSONObject startVillage = (JSONObject) setting.get(CurrentManager.getInstance().getCurrentStarting());
		String dungeonID = (String) startVillage.get(CurrentManager.getInstance().getCurrentDestination());
		
		Gdx.app.log("DEBUG", CurrentManager.getInstance().getCurrentStarting()+" and "+
				CurrentManager.getInstance().getCurrentDestination()+" and "+dungeonID);
		CurrentManager.getInstance().setCurrentDungeon(dungeonID);
	}
	
	// 전투 랜덤으로 발생
	public boolean isOccur() {
		return random.nextBoolean();
	}
	
	public void start() {
		setDungeon();
		new ScreenController(ScreenEnum.BATTLE);
	}
}
