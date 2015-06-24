package com.mygdx.test;

import com.mygdx.stage.RootOverlap2DStage;
import com.mygdx.state.StaticAssets;

public class GameStage extends RootOverlap2DStage {
	public GameStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("GameScene");
		addActor(sceneLoader.getRoot());
	}
}
