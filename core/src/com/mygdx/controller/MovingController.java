package com.mygdx.controller;

import com.badlogic.gdx.Gdx;

public class MovingController {

	String got;
	String key1, key2, key3;
	String delimiter = "-";

	public MovingController() {
	}

	public void ChangeDestination() {

	}

	public String checkStage() {
		/*
		 * if (key == haveRight) return "right"; } if (key == haveLeft) { return
		 * "left"; } if (key == haveBoth) { return "twin"; }
		 */
		Gdx.app.log("Test", "checkDirection");
		return "checkDirection";
	}

}
