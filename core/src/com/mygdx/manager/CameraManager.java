package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.uwsoft.editor.renderer.Overlap2DStage;

public class CameraManager {
	private OrthographicCamera cam;

	public enum CameraPosition {
		ABOVE_GAME_UI, BELOW_GAME_UI;
	}

	public void setCameraSize(Overlap2DStage stage,
			CameraPosition cameraPosition) {
		float width = stage.sceneLoader.getRoot().getWidth();
		float height = stage.sceneLoader.getRoot().getHeight();
		cam = new OrthographicCamera(width, height);
		stage.getViewport().setCamera(cam);
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
}
