package com.mygdx.manager;

import java.util.Random;

import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class EncounterManager {
	Random random = new Random();

	public EncounterManager() {
		
	}

	public void start() {
		setDungeon();
		new ScreenController(ScreenEnum.ENCOUNT);
	}

	void setDungeon() {
		String dungeonID = getDungeonID();
		CurrentState.getInstance().setCurrentDungeon(dungeonID);

		Gdx.app.log("DEBUG", CurrentState.getInstance().getVillageInfo()
				.getCurrentStarting()
				+ " and "
				+ CurrentState.getInstance().getVillageInfo()
						.getCurrentDestination() + " and " + dungeonID);
	}

	// 전투 랜덤으로 발생
	public boolean isOccur() {
		return random.nextBoolean();
	}

	// 출발지와 도착지의 정보를 받아서 해당하는 던전을 리턴.
	private String getDungeonID() {
		// 1. 던전 설정을 전부 불러와서
		JSONObject setting = (JSONObject) Assets.jsonObjectMap.get(
				"dungeon_json").get("setting");
		// 2. 출발하는 마을을 가져온 후
		JSONObject startVillage = (JSONObject) setting.get(CurrentState
				.getInstance().getVillageInfo().getCurrentStarting());
		// 3. 마을-목적지 사이에 있는 던전 정보를 반환한다.
		return (String) startVillage.get(CurrentState.getInstance()
				.getVillageInfo().getCurrentDestination());
	}
}
