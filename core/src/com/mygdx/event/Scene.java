package com.mygdx.event;


public interface Scene {

	void load();

	void showNextScene();

	boolean isNext();

	void show(float delta);
}
