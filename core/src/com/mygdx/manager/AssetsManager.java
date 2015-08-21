package com.mygdx.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class AssetsManager extends AssetManager {
	public ResourceManager rm = new ResourceManager();

	public void loadOverlapResources() {
		rm.initAllResources();
	}
}
