package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.PositionEnum.LocatePosition;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;

public class MovingManager {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private BattleManager battleManager;

	public void goToNode(String Node) {
		positionManager.setCurrentNodePath(Node);
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		goCurrentNode(nodeType);
	}

	public void goCurrentLocatePosition() {
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		if (positionManager.isInWorldMap()) {
			positionManager.setInWorldMap(false);
		}
		if (positionManager.getCurrentEventPositionType() != PositionEnum.EventPosition.NONE) {
			goBeforeEventPosition();
		} else {
			switch (positionManager.getCurrentLocatePositionType()) {
				case NODE :
					goCurrentNode(nodeType);
					break;
				case SUB_NODE :
					goCurrentSubNode(nodeType);
					break;
				case FIELD :
					screenFactory.show(ScreenEnum.FIELD);
					break;
				case DUNGEON :
					screenFactory.show(ScreenEnum.DUNGEON);
				default :
					Gdx.app.log("MovingManager", "NodeType정보 오류");
					break;
			}
		}
	}

	private void goBeforeEventPosition() {
		switch (positionManager.getCurrentEventPositionType()) {
			case BATTLE :
				positionManager.setCurrentEventPositionType(PositionEnum.EventPosition.NONE);
				goCurrentLocatePosition();
				break;
			case GREETING :
				screenFactory.show(ScreenEnum.GREETING);
				break;
			case WORLD_MAP :
				positionManager.setCurrentEventPositionType(EventPosition.NONE);
				screenFactory.show(ScreenEnum.STATUS);
				break;
			case LOG :
				positionManager.setCurrentEventPositionType(EventPosition.NONE);
				goCurrentLocatePosition();
				break;
			default :
				Gdx.app.log("MovingManager", "EventPosition 정보 오류");
				break;
		}
	}

	public void goPreviousPosition() {
		switch (positionManager.getCurrentLocatePositionType()) {
			case SUB_NODE :
				positionManager.setCurrentLocatePositionType(LocatePosition.NODE);
				screenFactory.show(ScreenEnum.findScreenEnum(positionManager.getCurrentNodeType().toString()));
				break;
			case NODE :
				positionManager.setCurrentLocatePositionType(LocatePosition.FIELD);
				screenFactory.show(ScreenEnum.FIELD);
				break;
			default :
				Gdx.app.log("MovingManager", "PositionEnum정보 오류");
		}
	}

	public void goCurrentNode(WorldNodeEnum.NodeType nodeType) {
		switch (nodeType) {
			case VILLAGE :
				screenFactory.show(ScreenEnum.VILLAGE);
				return;
			case DUNGEON_ENTRANCE :
				screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
				return;
			case FORK :
				screenFactory.show(ScreenEnum.FORK);
				return;
		}
	}

	private void goCurrentSubNode(WorldNodeEnum.NodeType nodeType) {
		switch (nodeType) {
			case VILLAGE :
				screenFactory.show(ScreenEnum.BUILDING);
				return;
			case DUNGEON_ENTRANCE :
				screenFactory.show(ScreenEnum.DUNGEON);
				return;
			case FORK :
				screenFactory.show(ScreenEnum.FIELD); // FIXME
				return;
		}
	}
}
