package com.mygdx.controller;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.ScreenManager;
import com.mygdx.model.EventInfo;

public class ScreenController extends Task {

	private ScreenEnum screen = null;
	private ScreenManager screenManager = ScreenManager.getInstance();

	public ScreenController(ScreenEnum screen) {
		this.screen = screen;
		run();
	}

	public ScreenController(ScreenEnum screen, EventInfo eventInfo) {
		this.screen = screen;
	}

	@Override
	public void run() {
		/* easily implemented screen switching thanks to singleton pattern */
		screenManager.show(screen);
	}

}
