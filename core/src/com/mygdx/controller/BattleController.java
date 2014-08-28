package com.mygdx.controller;

import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;
import com.mygdx.util.CurrentManager;

public class BattleController {
	ScreenController screenController;
	ScreenEnum screenEnum;
	
	Random random;
	JSONParser parser = new JSONParser();
	JSONObject villages;
	String key1, key2, key3;
	String delimiter = "-";
	
	BattleController() {
		random = new Random();
	}
	
	public void setDungeon() {
		JSONObject setting = (JSONObject) Assets.dungeon_json.get("setting");
		JSONObject startVillage = (JSONObject) setting.get(CurrentManager.getInstance().getCurrentStarting());
		String dungeonID = (String) startVillage.get(CurrentManager.getInstance().getCurrentDestination());
		CurrentManager.getInstance().setCurrentDungeon(dungeonID);
	}
	
	// 전투 랜덤으로 발생
	public boolean isOccur() {
		return random.nextBoolean();
	}
	
	public void start() {
		
		new ScreenController(ScreenEnum.BATTLE);
	}
}
