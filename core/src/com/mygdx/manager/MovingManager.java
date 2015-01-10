package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.VillageInfo;
import com.mygdx.model.WorldMapInfo.RodeInfo;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class MovingManager {

	private RodeInfo currentRodeInfo;

	private int roadLength;
	private int leftLength;
	public static int temp;
	private boolean battled;

	private EncounterManager encounter;
	private String currentPosition;
	private String currentDestination;
	private String currentStartingpoint;

	VillageInfo villageInfo;

	public MovingManager() {
		encounter = new EncounterManager();
		battled = false;

		villageInfo = CurrentState.getInstance().getVillageInfo();

		currentDestination = villageInfo.getCurrentDestination();
		currentPosition = villageInfo.getCurrentPosition();
		currentStartingpoint = villageInfo.getCurrentStarting();

		currentRodeInfo = Assets.worldMapInfo.getRodeInfo().get(currentPosition);

		roadLength = currentRodeInfo.getLength();
		leftLength = roadLength;
		if (battled) {
			leftLength = temp;
			battled = false;
		}

	}

	public int getLeftLength() {
		return leftLength;
	}

	public String getRoadName() {
		return currentRodeInfo.getName();
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
		if (isLeft()) {
			thenEncounterMonster();
		} else {
			thenGoVillage();
		}

	}

	private void thenEncounterMonster() {
		leftLength--;
		if (encounter.isOccur()) {
			temp = leftLength;
			battled = true;
			encounter.start();
		}
	}

	private void thenGoVillage() {
		villageInfo.setCurrentState(Assets.worldMapInfo.getNodeInfo()
				.get(currentDestination).getType());

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

	private boolean isLeft() {
		return (leftLength > 0) ? true : false;
	}

	public void goBackward() {

		String temp = currentDestination;

		currentDestination = currentStartingpoint;
		currentStartingpoint = temp;

		leftLength = roadLength - leftLength;
	}

}
