package com.mygdx.manager;

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
			case BELOW_GAME_UI:
				stage.getCamera().position.set(width / 2, 0, 0);
			default:
				stage.getCamera().position.set(width / 2, height * 0.25f, 0);
		}
	}
}
