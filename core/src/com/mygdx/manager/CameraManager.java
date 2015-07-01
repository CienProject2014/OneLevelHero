package com.mygdx.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.Overlap2DStage;

public class CameraManager {
	public void setCameraSize(Stage stage, float width, float height) {
		stage.getViewport().setCamera(new OrthographicCamera(width, height));
		stage.getCamera().position.set(width / 2, height / 2, 0);
	}

	public void setCameraSize(Stage stage) {
		setCameraSize(stage, stage.getWidth(), stage.getHeight());
	}

	public void setCameraSize(Overlap2DStage stage) {
		setCameraSize(stage, stage.sceneLoader.getRoot().getWidth(),
				stage.sceneLoader.getRoot().getHeight());
	}
}
