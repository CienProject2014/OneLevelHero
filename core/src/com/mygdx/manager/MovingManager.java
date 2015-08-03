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
	private StorySectionManager storySectionManager;
	@Autowired
	private EventManager eventManager;

	public void goCurrentPosition() {
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		switch (positionManager.getCurrentPositionType()) {
			case LOG:
				if (eventManager.getCurrentNpc().getName().equals("yongsa")) {
					positionManager.setCurrentPositionType(PositionEnum.NODE);
					goCurrentNode(nodeType);
				} else {
					screenFactory.show(ScreenEnum.LOG);
				}
				break;
			case NODE_EVENT:
				if (eventManager.isGreeting()) {
					positionManager.setCurrentPositionType(PositionEnum.NODE);
					eventManager.setGreeting(false);
					goCurrentNode(nodeType);
				} else {
					screenFactory.show(ScreenEnum.GREETING);
				}
				break;
			case SUB_NODE_EVENT:
				if (eventManager.isGreeting()) {
					positionManager
							.setCurrentPositionType(PositionEnum.SUB_NODE);
					eventManager.setGreeting(false);
					goCurrentSubNode(nodeType);
				} else {
					screenFactory.show(ScreenEnum.GREETING);
				}
				break;
			case BATTLE_EVENT:
				if (eventManager.isGreeting()) {
					eventManager.setGreeting(false);
					positionManager.setCurrentPositionType(PositionEnum.BATTLE);
					screenFactory.show(ScreenEnum.BATTLE);
				} else {
					screenFactory.show(ScreenEnum.GREETING);
				}
				break;
			case NODE:
				goCurrentNode(nodeType);
				break;
			case SUB_NODE:
				goCurrentSubNode(nodeType);
				break;
			case FIELD:
				screenFactory.show(ScreenEnum.FIELD);
				break;
			case BATTLE:
				screenFactory.show(ScreenEnum.BATTLE);
				break;
			default:
				Gdx.app.log("MovingManager", "NodeType정보 오류");
				break;
		}
	}

	public void goPreviousPosition() {
		switch (positionManager.getCurrentPositionType()) {
			case SUB_NODE:
				positionManager.setCurrentPositionType(PositionEnum.NODE);
				screenFactory.show(ScreenEnum.findScreenEnum(positionManager
						.getCurrentNodeType().toString()));
				break;
			case NODE:
				positionManager.setCurrentPositionType(PositionEnum.FIELD);
				screenFactory.show(ScreenEnum.FIELD);
				break;
			default:
				Gdx.app.log("MovingManager", "PositionEnum정보 오류");
		}
	}

	private void goCurrentNode(WorldNodeEnum.NodeType nodeType) {
		switch (nodeType) {
			case VILLAGE:
				screenFactory.show(ScreenEnum.VILLAGE);
				break;
			case DUNGEON_ENTRANCE:
				screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
				break;
			case FORK:
				screenFactory.show(ScreenEnum.FIELD); //FIXME
				break;
		}
	}

	private void goCurrentSubNode(WorldNodeEnum.NodeType nodeType) {
		switch (nodeType) {
			case VILLAGE:
				screenFactory.show(ScreenEnum.BUILDING);
				break;
			case DUNGEON_ENTRANCE:
				screenFactory.show(ScreenEnum.DUNGEON);
				break;
			case FORK:
				screenFactory.show(ScreenEnum.FIELD); //FIXME
				break;
		}
	}
}
