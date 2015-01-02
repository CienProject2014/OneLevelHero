package com.mygdx.stage;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class WorldMapStage extends Overlap2DStage {
	private Touchpad touchpad;
    private TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;

	public WorldMapStage() {
	
//Create a touchpad skin    
		
        
		
		initSceneLoader();
		sceneLoader.loadScene("MainScene");
		CompositeItem sceneActor = sceneLoader.sceneActor;
		addActor(sceneActor);
		
		
	}

	

	

}
