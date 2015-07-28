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

	public void goCurrentPosition() {
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		switch (positionManager.getCurrentPositionType()) {
			case NODE:
				goCurrentNode(nodeType);
				break;
			case SUB_NODE:
				goCurrentSubNode(nodeType);
				break;
			case FIELD:
				screenFactory.show(ScreenEnum.FIELD);
				break;
			default:
				Gdx.app.log("MovingManager", "NodeType정보 오류");
				break;
		}
	}
}
