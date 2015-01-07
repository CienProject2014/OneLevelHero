package com.mygdx.stage;


import com.mygdx.script.ButtonScript;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class WorldMapStage extends Overlap2DStage {
	public WorldMapStage() {  
		initSceneLoader();
		sceneLoader.loadScene("MainScene");
		
		ButtonScript buttonScript = new ButtonScript(this);
		sceneLoader.getRoot().addScript(buttonScript);
		addActor(sceneLoader.getRoot());		
	}
}
