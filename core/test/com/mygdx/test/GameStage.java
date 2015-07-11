package com.mygdx.test;

import com.mygdx.assets.StaticAssets;
import com.mygdx.stage.BaseOverlapStage;

public class GameStage extends BaseOverlapStage {
	public GameStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("GameScene");
		addActor(sceneLoader.getRoot());
	}
}
