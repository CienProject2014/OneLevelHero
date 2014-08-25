package com.mygdx.resource;

public interface Scene {
	void load();

	void showNextScene();

	boolean isNext();

	void show(float delta);
}
