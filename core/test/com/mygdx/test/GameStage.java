package com.mygdx.test;

import com.mygdx.stage.OverlapStage;
import com.mygdx.state.StaticAssets;

public class GameStage extends OverlapStage {
	public GameStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("GameScene");
		addActor(sceneLoader.getRoot());
	}
}
