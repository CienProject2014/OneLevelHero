package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.assets.StaticAssets;
import com.mygdx.stage.BaseOneLevelStage;
import com.mygdx.stage.BaseOverlapStage;

public class CameraManager {
	private OrthographicCamera cam;

	public enum CameraPosition {
		ABOVE_GAME_UI, BELOW_GAME_UI;
	}

	public CameraManager() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f, 0);
	}

	public void setCameraSize(BaseOverlapStage stage, CameraPosition cameraPosition) {
		float width = stage.sceneLoader.getRoot().getWidth();
		float height = stage.sceneLoader.getRoot().getHeight();
		stage.getViewport().setCamera(new OrthographicCamera(width, height));
		switch (cameraPosition) {
		case ABOVE_GAME_UI:
			stage.getCamera().position.set(width / 2, height * 0.25f, 0);
			break;
		case BELOW_GAME_UI:
			stage.getCamera().position.set(width / 2, height * 0.5f, 0);
			break;
		default:
			Gdx.app.debug("CameraManager", "CameraPositionEnum is not set");
			stage.getCamera().position.set(width / 2, height * 0.25f, 0);
			break;
		}
	}

	public void stretchToDevice(BaseOneLevelStage stage) {
		stage.getViewport().setCamera(cam);
	}
}
