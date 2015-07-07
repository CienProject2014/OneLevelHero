package com.mygdx.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TouchListenerWithLog extends InputListener {
	private String tag, msg;
	private Runnable func;

	public TouchListenerWithLog(String tag, String msg, Runnable func) {
		this.tag = tag;
		this.msg = msg;
		this.func = func;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		Gdx.app.log(tag, msg);
		func.run();
	}
}
