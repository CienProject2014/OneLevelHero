package com.mygdx.manager;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class AssetsManager extends AssetManager {
	public ResourceManager rm = new ResourceManager();

	public void loadOverlapResources() {
		rm.loadProjectVO();
		rm.settingScene("skill_scene");
		rm.settingScene("save_scene");
	}

	public void initMultiScene(String name) {
		String path = splitString(name);
		Gdx.app.log("load", "orig" + File.separator + path + "pack.atlas");
		load("orig" + File.separator + path + "pack.atlas", TextureAtlas.class);
		finishLoading();
		TextureAtlas atlas = get("orig/" + path + "pack.atlas", TextureAtlas.class);
		rm.setMainPack(atlas);
		rm.initScene(name);
	}

	public void initScene(String name) {
		String path = splitString(name);
		// 1. 방금 전에 불린 Scene과 같지 않을 경우 unload한다.
		// 2. SkillScene은 항상 unload하지 않는다.
		boolean check1 = rm.myScene.contains(name);
		boolean check2 = rm.myScene.contains(rm.preparedSceneNames);
		if (check1 == false) {
			if (check2 == false) {
				if (name.equals(rm.preparedSceneNames)) {
				} else {
					if (isLoaded("orig/" + splitString(rm.preparedSceneNames) + "pack.atlas")) {
						Gdx.app.log("unload", "orig/" + splitString(rm.preparedSceneNames) + "pack.atlas");
						unload("orig/" + splitString(rm.preparedSceneNames) + "pack.atlas");
					}
				}
			}
		}

		if (rm.myScene.contains(name)) {
		} else if (name.equals(rm.preparedSceneNames)) {
		} else {
			Gdx.app.log("load", "orig" + File.separator + path + "pack.atlas");
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
