package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.CurrentPosition;
import com.mygdx.model.CurrentPosition.CurrentMovingInfo;
import com.mygdx.state.CurrentState;

public class MovingManager {
	private static CurrentPosition currentPosition = CurrentState.getInstance()
			.getCurrentPosition();
	private static CurrentMovingInfo currentMovingInfo = currentPosition
			.getCurrentMovingInfo();
	private static int roadLength = currentMovingInfo.getRoadLength();
	private static int leftRoadLength = currentMovingInfo.getLeftRoadLength();

	public static void ChangeDestination() {
	}

	public static String checkStage() {
		Gdx.app.log("Test", "checkDirection");
		return "checkDirection";
	}

	public static void goForward() {
		Gdx.app.log("test", "goForward");
		if (isRoadLeft()) {
			movingRoad();
			leftRoadLength--;
		} else {
			goIntoDestinationNode();
		}
	}

	public static void goBackward() {
		Gdx.app.log("test", "goForward");
		if (roadLength > leftRoadLength) {
			movingRoad();
			leftRoadLength++;
		} else {
			goIntoDestinationNode();
		}
	}

	private static void movingRoad() {
		if (EncounterManager.isBattleOccured()) {
			EncounterManager.encountEnemy();
		}
	}

	private static void goIntoDestinationNode() {
		//목적지 노드에 도착해서 현재 위치로 설정함
		currentPosition.setCurrentNode(currentMovingInfo.getDestinationNode());

		if (currentPosition.getCurrentNode().equals("village")) {
			new ScreenController(ScreenEnum.VILLAGE);
		} else if (currentPosition.getCurrentNode().equals("dungeon")) {
			new ScreenController(ScreenEnum.VILLAGE);
		} else if (currentPosition.getCurrentNode().equals("turningpoint")) {
			new ScreenController(ScreenEnum.WORLD_MAP);
		} else {
		}
	}

	private static boolean isRoadLeft() {
		return (leftRoadLength > 0) ? true : false;
	}

}
