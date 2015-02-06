package com.mygdx.enums;

public enum SceneEnum {
	BATTLE("battle_scene"), WORLD_MAP("world_map_scene");

	private String sceneName;

	SceneEnum(String sceneName) {
		this.sceneName = sceneName;
	}

	@Override
	public String toString() {
		return sceneName;
	}
}
