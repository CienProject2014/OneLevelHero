package com.mygdx.message;

import com.badlogic.gdx.Gdx;

public class AlertMessage implements Message {

	public void write(String message) {
		Gdx.app.log("AlertMessage", message);
	}
}
