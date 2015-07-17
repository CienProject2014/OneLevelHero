package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

public class MovingManager {
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private EncounterManager encounterManager;

	public void goForward() {
		if (isRoadLeft()) {
			minusLeftRoadLength();
			movingRoad();
		} else {
			// 목적지 노드에 도착해서 현재 위치로 설정함
			positionInfo.setCurrentNode(movingInfo.getDestinationNode());
			goIntoCurrentNode();
		}
	}

	public void goBackward() {
		if (!isRoadFull()) {
			plusLeftRoadLength();
			movingRoad();
		} else {
			// 원래 노드로 다시 돌아옴
			positionInfo.setCurrentNode(movingInfo.getStartNode());
			goIntoCurrentNode();
		}
	}

	private void plusLeftRoadLength() {
		movingInfo.setLeftRoadLength(movingInfo.getLeftRoadLength() + 1);
	}

	public void minusLeftRoadLength() {
		movingInfo.setLeftRoadLength(movingInfo.getLeftRoadLength() - 1);
	}

	private void movingRoad() {
		if (encounterManager.isBattleOccured()) {
			encounterManager.encountEnemy();
		}
	}

	private void goIntoCurrentNode() {
		String placeType = worldMapAssets.getWorldNodeInfo(
				positionInfo.getCurrentNode()).getType();
		switch (PlaceEnum.findPlaceEnum(placeType)) {
		case VILLAGE:
			screenFactory.show(ScreenEnum.VILLAGE);
			break;
		case DUNGEON:
			screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
			break;
		case FORK:
			screenFactory.show(ScreenEnum.VILLAGE);
			break;
		default:
			screenFactory.show(ScreenEnum.VILLAGE);
			Gdx.app.debug("MovingManager", "CurrentNode 타입 오류");
			break;
		}
	}

	private boolean isRoadLeft() {
		return (movingInfo.getLeftRoadLength() > 0) ? true : false;
	}

	private boolean isRoadFull() {
		return (movingInfo.getRoadLength() <= movingInfo.getLeftRoadLength()) ? true
				: false;
	}
}
