package com.mygdx.test;

import com.mygdx.stage.BaseOverlapStage;
import com.mygdx.state.StaticAssets;

public class GameStage extends BaseOverlapStage {
	public GameStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("GameScene");
		addActor(sceneLoader.getRoot());
	}
}
