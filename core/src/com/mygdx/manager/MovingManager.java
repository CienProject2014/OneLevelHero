package com.mygdx.manager;

import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.VillageInfo;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class MovingManager {

	JSONObject roadJson;
	JSONObject roadInfo;

	int roadlength;
	int leftlength;
	public static int temp;
	boolean battled;

	EncounterManager encounter;
	String currentPosition;
	String currentDestination;
	String currentStartingpoint;

	VillageInfo villageInfo;

	public MovingManager() {
		encounter = new EncounterManager();
		battled = false;

		villageInfo = CurrentState.getInstance().getVillageInfo();

		currentDestination = villageInfo.getCurrentDestination();
		currentPosition = villageInfo.getCurrentPosition();
		currentStartingpoint = villageInfo.getCurrentStarting();

		roadJson = (JSONObject) Assets.jsonObjectMap.get("worldmap_json").get(
				"Road");

		roadInfo = (JSONObject) roadJson.get(currentPosition);

		roadlength = Integer.parseInt((String) roadInfo.get("length"));
		leftlength = roadlength;
		if (battled) {
			leftlength = temp;
			battled = false;
		}

	}

	public int getLeftLength() {
		return leftlength;
	}

	public String getRoadName() {
		return (String) roadInfo.get("name");
	}

	public String getCurrentDestination() {
		return currentDestination;
	}

	public void ChangeDestination() {

	}

	public String checkStage() {
		Gdx.app.log("Test", "checkDirection");
		return "checkDirection";
	}

	public void goForward() {
		Gdx.app.log("test", "goForward");
		if (leftlength > 0) {
			leftlength--;
			if (encounter.isOccur()) {
				temp = leftlength;
				battled = true;
				encounter.start();
			}
		}
		if (leftlength == 0) {

			villageInfo.setCurrentState(Assets.worldHashmap.get(
					currentDestination).getType());

			villageInfo.setCurrentPosition(currentDestination);

			villageInfo.setCurrentDestination(null);

			villageInfo.setCurrentStarting(null);

			String currentState = villageInfo.getCurrentState();

			if (currentState.equals("village")) {

				new ScreenController(ScreenEnum.VILLAGE);

			} else if (currentState.equals("dungeon")) {

				new ScreenController(ScreenEnum.VILLAGE);

			} else if (currentState.equals("turningpoint")) {

				new ScreenController(ScreenEnum.WORLD_MAP);

			} else {

			}
		}

	}

	public void goBackward() {

		String temp = currentDestination;
		
		currentDestination = currentStartingpoint;
		currentStartingpoint = temp;

		leftlength = roadlength - leftlength;
	}

}
