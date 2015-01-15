package com.mygdx.manager;

import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.CurrentPosition;
import com.mygdx.model.CurrentPosition.CurrentMovingInfo;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class MovingManager {
	private static CurrentPosition currentPosition = CurrentState.getInstance()
			.getCurrentPosition();
	private static CurrentMovingInfo currentMovingInfo = currentPosition
			.getCurrentMovingInfo();
	private static int roadLength = currentMovingInfo.getRoadLength();
	private static int leftRoadLength = currentMovingInfo.getLeftRoadLength();

	public static int getLeftRoadLength() {
		return leftRoadLength;
	}

	public static void setLeftRoadLength(int leftRoadLength) {
		MovingManager.leftRoadLength = leftRoadLength;
	}

	public static void ChangeDestination() {
	}

	public static String checkStage() {
		return "checkDirection";
	}

	public static void goForward() {
		if (isRoadLeft()) {
			leftRoadLength--;
			movingRoad();
		} else {
			goIntoCurrentNode();
		}
	}

	public static void goBackward() {
		if (roadLength > leftRoadLength) {
			leftRoadLength++;
			movingRoad();
		} else {
			//목적지 노드에 도착해서 현재 위치로 설정함
			currentPosition.setCurrentNode(currentMovingInfo
					.getDestinationNode());
			goIntoCurrentNode();
		}
	}

	private static void movingRoad() {
		if (EncounterManager.isBattleOccured()) {
			EncounterManager.encountEnemy();
		}
	}

	private static void goIntoCurrentNode() {
		switch (Assets.worldNodeInfoMap.get(currentPosition.getCurrentNode())
				.getType()) {
			case "village":
				new ScreenController(ScreenEnum.VILLAGE);
				break;
			case "dungeon":
				new ScreenController(ScreenEnum.VILLAGE);
				break;
			case "turningpoint":
				new ScreenController(ScreenEnum.WORLD_MAP);
				break;
			default:
				new ScreenController(ScreenEnum.VILLAGE);
				break;
		}
	}

	private static boolean isRoadLeft() {
		return (leftRoadLength > 0) ? true : false;
	}

}
