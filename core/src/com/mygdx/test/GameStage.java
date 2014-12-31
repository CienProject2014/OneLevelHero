package com.mygdx.test;

import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.ImageItem;

public class GameStage extends Overlap2DStage {
	private ImageItem sheep;

	public GameStage() {
		initSceneLoader();
		sceneLoader.loadScene("MainScene");
		addActor(sceneLoader.getRoot());
	}
}
