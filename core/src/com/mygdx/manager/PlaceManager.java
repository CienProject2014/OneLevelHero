package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

public class PlaceManager {
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private ScreenFactory screenFactory;

	public void goPreviousPlace() {
		switch (positionInfo.getCurrentPlace()) {
		case BUILDING:
			screenFactory.show(ScreenEnum.BUILDING);
			break;
		case VILLAGE:
			screenFactory.show(ScreenEnum.VILLAGE);
			break;
		case DUNGEON:
			// screenFactory.show(ScreenEnum.DUNGEON);
			screenFactory.show(ScreenEnum.VILLAGE); // FIXME
			break;
		case FORK:
			// screenFactory.show(ScreenEnum.FORK);
			screenFactory.show(ScreenEnum.VILLAGE); // FIXME
			break;
		default:
			Gdx.app.log("EventScreen",
					"positionInfo.getCurrentPlace() is not valid");
			break;
		}
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}
}
