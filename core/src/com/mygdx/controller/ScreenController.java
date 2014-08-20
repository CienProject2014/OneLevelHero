package com.mygdx.controller;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.util.ScreenEnum;
import com.mygdx.util.ScreenManager;

public class ScreenController extends Task {

	private ScreenEnum screen = null;

	public ScreenController(ScreenEnum screen) {
		this.screen = screen;
		run();
	}

	@Override
	public void run() {
		/* easily implemented screen switching thanks to singleton pattern */
		ScreenManager.getInstance().show(screen);
	}

}
