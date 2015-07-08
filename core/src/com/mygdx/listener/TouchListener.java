package com.mygdx.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TouchListener extends InputListener {
	private Runnable func;

	public TouchListener(Runnable func) {
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
		func.run();
	}
}
