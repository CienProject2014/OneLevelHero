package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.mygdx.assets.Assets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.CameraManager;

public class BaseOneLevelStage extends Stage {
	@Autowired
	protected Assets assets;

	@Autowired
	protected ScreenFactory screenFactory;

	@Autowired
	protected CameraManager cameraManager;

	@Autowired
	protected PartyInfo partyInfo;
	@Autowired
	protected TimeInfo timeInfo;

	@Autowired
	protected MovingInfo movingInfo;

	protected Stack tableStack;

	public Stage makeStage() {
		tableStack = new Stack();
		tableStack.setWidth(StaticAssets.BASE_WINDOW_WINDTH);
		tableStack.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		this.addActor(tableStack);
		cameraManager.stretchToDevice(this);
		return this;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

	public PartyInfo getPartyInfo() {
		return partyInfo;
	}

	public void setPartyInfo(PartyInfo partyInfo) {
		this.partyInfo = partyInfo;
	}

	public TimeInfo getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(TimeInfo timeInfo) {
		this.timeInfo = timeInfo;
	}

	public MovingInfo getMovingInfo() {
		return movingInfo;
	}

	public void setMovingInfo(MovingInfo movingInfo) {
		this.movingInfo = movingInfo;
	}

}
