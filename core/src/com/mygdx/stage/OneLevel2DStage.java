package com.mygdx.stage;

import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class OneLevel2DStage extends Overlap2DStage {
	public void initScene(String sceneName) {
		ResourceManager rm = new ResourceManager();
		rm.loadProjectVO();
		rm.initScene(sceneName);
		sceneLoader = new SceneLoader(essentials);
		essentials.rayHandler.setWorld(essentials.world);
		essentials.rm = rm;
	}
}
