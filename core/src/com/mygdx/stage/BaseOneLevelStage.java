package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.mygdx.assets.Assets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.CameraManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TimeManager;

public class BaseOneLevelStage extends Stage {
	@Autowired
	protected Assets assets;
	@Autowired
	protected MovingManager movingManager;
	@Autowired
	protected PartyManager partyManager;
	@Autowired
	protected ScreenFactory screenFactory;
	@Autowired
	protected CameraManager cameraManager;
	@Autowired
	protected TimeManager timeManager;

	protected Stack tableStack;

	public Stage makeStage() {
		tableStack = new Stack();
		tableStack.setWidth(StaticAssets.BASE_WINDOW_WINDTH);
		tableStack.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		this.addActor(tableStack);
		cameraManager.stretchToDevice(this);
		return this;
	}
}
