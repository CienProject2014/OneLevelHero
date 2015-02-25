package com.mygdx.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.uwsoft.editor.renderer.Overlap2DStage;

public class CameraManager {
	private OrthographicCamera cam;

	public void setCameraSize(Overlap2DStage stage) {
		float width = stage.sceneLoader.getRoot().getWidth();
		float height = stage.sceneLoader.getRoot().getHeight();
		cam = new OrthographicCamera(width, height);
		stage.getViewport().setCamera(cam);
		stage.getCamera().position.set(width / 2, height * 0.25f, 0);
	}
}
