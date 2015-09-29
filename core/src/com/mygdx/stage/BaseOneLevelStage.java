package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.math.Vector2;
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
	protected transient Assets assets;
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

	public Stack getTableStack() {
		return tableStack;
	}

	public void setTableStack(Stack tableStack) {
		this.tableStack = tableStack;
	}

	protected Stack tableStack;

	// touch 이벤트에서 사용될 터치 위치
	// screenX, screenY을 직접 사용하는 대신 이 필드를 사용한다.
	protected Vector2 touched = new Vector2();

	public Stage makeStage() {
		tableStack = new Stack();
		tableStack.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		tableStack.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		this.addActor(tableStack);
		cameraManager.stretchToDevice(this);
		return this;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touched = screenToStageCoordinates(new Vector2(screenX, screenY));
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touched = screenToStageCoordinates(new Vector2(screenX, screenY));
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = screenToStageCoordinates(new Vector2(screenX, screenY));
		return super.touchUp(screenX, screenY, pointer, button);
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}
}
