package com.mygdx.manager;

import java.io.File;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class AssetsManager extends AssetManager {
	public ResourceManager rm = new ResourceManager();

	public void loadOverlapResources() {
		rm.loadProjectVO();
	}

	public void initScene(String name) {
		String path = splitString(name);
		if (name != rm.preparedSceneNames
				&& isLoaded("orig" + File.separator + splitString(rm.preparedSceneNames) + "pack.atlas")
				&& name != "skill_scene") {
			System.out.println("test");
			unload("orig" + File.separator + splitString(rm.preparedSceneNames) + "pack.atlas");
			finishLoading();
		}
		if (name == rm.preparedSceneNames) {
		} else {
			load("orig" + File.separator + path + "pack.atlas", TextureAtlas.class);
			finishLoading();
		}

		TextureAtlas atlas = get("orig/" + path + "pack.atlas", TextureAtlas.class);
		rm.setMainPack(atlas);
		rm.initScene(name);
	}

	private String splitString(String name) {
		String[] result = name.split("_");
		String path = "";
		for (int i = 0; i < result.length - 1; i++) {
			path += result[i];
			path += "_";
		}
		return path;
	}
}
