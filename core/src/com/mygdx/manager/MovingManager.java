package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.PositionEnum;
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
		positionManager.setCurrentNodeName(Node);
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		goCurrentNode(nodeType);
	}

	public void goCurrentPosition() {
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		if (positionManager.isInWorldMap()) {
			positionManager.setInWorldMap(false);
		}
		if (battleManager.isInBattle()) {
			screenFactory.show(ScreenEnum.BATTLE);
			return;
		}
		switch (positionManager.getCurrentPositionType()) {
			case LOG :
				if (eventManager.getCurrentNpc().getName().equals("yongsa")) {
					if (positionManager.getBeforePositionType().equals(PositionEnum.SUB_NODE)) {
						positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
						goCurrentSubNode(nodeType);
					} else if (positionManager.getBeforePositionType().equals(PositionEnum.NODE)) {
						positionManager.setCurrentPositionType(PositionEnum.NODE);
						goCurrentNode(nodeType);
					} else {
						screenFactory.show(ScreenEnum.FIELD);
					}
				} else {
					screenFactory.show(ScreenEnum.LOG);
				}
				break;
			case NODE_EVENT :
				if (eventManager.isGreeting()) {
					positionManager.setCurrentPositionType(PositionEnum.NODE);
					eventManager.setGreeting(false);
					goCurrentNode(nodeType);
				} else {
					screenFactory.show(ScreenEnum.GREETING);
				}
				break;
			case SUB_NODE_EVENT :
				if (eventManager.isGreeting()) {
					positionManager.setCurrentPositionType(PositionEnum.SUB_NODE);
					eventManager.setGreeting(false);
					goCurrentSubNode(nodeType);
				} else {
					screenFactory.show(ScreenEnum.GREETING);
				}
				break;
			case BATTLE_EVENT :
				if (eventManager.isGreeting()) {
					eventManager.setGreeting(false);
					screenFactory.show(ScreenEnum.BATTLE);
				} else {
					screenFactory.show(ScreenEnum.GREETING);
				}
				break;
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

	public void goPreviousPosition() {
		switch (positionManager.getCurrentPositionType()) {
			case SUB_NODE :
				positionManager.setCurrentPositionType(PositionEnum.NODE);
				screenFactory.show(ScreenEnum.findScreenEnum(positionManager.getCurrentNodeType().toString()));
				break;
			case NODE :
				positionManager.setCurrentPositionType(PositionEnum.FIELD);
				screenFactory.show(ScreenEnum.FIELD);
				return;
			case FIELD :
				goBeforeBattlePosition();
				break;
			default :
				Gdx.app.log("MovingManager", "PositionEnum정보 오류");
		}
	}

	private void goBeforeBattlePosition() {
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();

		switch (battleManager.getBeforePosition()) {
			case NODE :
				goCurrentNode(nodeType);
				break;
			case SUB_NODE :
				goCurrentSubNode(nodeType);
				break;
			case FIELD :
				screenFactory.show(ScreenEnum.FIELD);
				return;
			default :
				Gdx.app.log("MovingManager", "BeforeNodeType정보 오류(" + battleManager.getBeforePosition() + ")");
				return;
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
				screenFactory.show(ScreenEnum.FIELD); // FIXME
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
