package com.mygdx.stage;

import com.uwsoft.editor.renderer.Overlap2DStage;

public class WorldMapStage extends Overlap2DStage {
	public WorldMapStage() {
		initSceneLoader();
		sceneLoader.loadScene("MainScene");
		addActor(sceneLoader.getRoot());
	}
}
