package com.mygdx.test;

import com.mygdx.assets.StaticAssets;
import com.mygdx.stage.BaseOverlapStage;

public class GameStage extends BaseOverlapStage {
	public GameStage() {
		if (!StaticAssets.rm.searchSceneNames("GameScene")) {
			StaticAssets.rm.initScene("GameScene");
		}
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("GameScene");
		addActor(sceneLoader.getRoot());
	}
}
