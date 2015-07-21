package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.assets.Assets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.CameraManager;
import com.mygdx.manager.CameraManager.CameraStateEnum;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.PositionManager;
import com.uwsoft.editor.renderer.Overlap2DStage;

public class BaseOverlapStage extends Overlap2DStage {
	@Autowired
	protected Assets assets;
	@Autowired
	protected ScreenFactory screenFactory;
	@Autowired
	protected CameraManager cameraManager;
	@Autowired
	protected PartyManager partyManager;
	@Autowired
	protected PositionManager positionManager;

	protected OrthographicCamera orthographicCamera = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH,
			StaticAssets.BASE_WINDOW_HEIGHT);

	protected CameraStateEnum cameraState = CameraStateEnum.STATIC;

	protected Vector3 cameraPosition = new Vector3();

	public static final int MOVING_SPEED = 10;

	@Override
	public void act() {
		cameraManager.moveCamera(this);
	}

	public OrthographicCamera getOrthographicCamera() {
		return orthographicCamera;
	}

	public CameraStateEnum getCameraState() {
		return cameraState;
	}

	public void setCameraState(CameraStateEnum cameraState) {
		this.cameraState = cameraState;
	}

}
