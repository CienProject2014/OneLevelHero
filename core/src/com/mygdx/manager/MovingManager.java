package com.mygdx.manager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.Assets;

@Component
public class MovingManager {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private EncounterManager encounterManager;

	private int roadLength;
	private int leftRoadLength;

	@PostConstruct
	public void init() {
		roadLength = movingInfo.getRoadLength();
		leftRoadLength = movingInfo.getLeftRoadLength();
	}

	public int getLeftRoadLength() {
		return leftRoadLength;
	}

	public void setRoadLength(int roadLength) {
		this.roadLength = roadLength;
	}

	public void setLeftRoadLength(int leftRoadLength) {
		movingInfo.setLeftRoadLength(leftRoadLength);
	}

	public void ChangeDestination() {
	}

	public String checkStage() {
		return "checkDirection";
	}

	public void goForward() {
		if (isRoadLeft()) {
			minusLeftRoadLength();
			movingRoad();
		} else {
			goIntoCurrentNode();
		}
	}

	public void goBackward() {
		if (!isRoadFull()) {
			plusLeftRoadLength();
			movingRoad();
		} else {
			//목적지 노드에 도착해서 현재 위치로 설정함
			positionInfo.setCurrentNode(movingInfo.getDestinationNode());
			goIntoCurrentNode();
		}
	}

	private void plusLeftRoadLength() {
		leftRoadLength += 1;
		movingInfo.setLeftRoadLength(leftRoadLength);
	}

	public void minusLeftRoadLength() {
		leftRoadLength -= 1;
		movingInfo.setLeftRoadLength(leftRoadLength);
	}

	private void movingRoad() {
		if (encounterManager.isBattleOccured()) {
			encounterManager.encountEnemy();
		}
	}

	private void goIntoCurrentNode() {
		switch (Assets.worldNodeInfoMap.get(positionInfo.getCurrentNode())
				.getType()) {
			case "village":
				screenFactory.show(ScreenEnum.VILLAGE);
				break;
			case "dungeon":
				screenFactory.show(ScreenEnum.VILLAGE);
				break;
			case "turningpoint":
				screenFactory.show(ScreenEnum.WORLD_MAP);
				break;
			default:
				screenFactory.show(ScreenEnum.VILLAGE);
				break;
		}
	}

	private boolean isRoadLeft() {
		return (leftRoadLength > 0) ? true : false;
	}

	private boolean isRoadFull() {
		return (roadLength > leftRoadLength) ? true : false;
	}

}
