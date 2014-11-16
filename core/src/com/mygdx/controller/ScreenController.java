package com.mygdx.controller;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.Manager;
import com.mygdx.manager.ScreenManager;

public class ScreenController extends Task {

	private ScreenEnum screen = null;
	public static Manager stageManager;

	public ScreenController(ScreenEnum screen) {
		this.screen = screen;
		run();
	}
    
	@Override
	public void run() {
		/* easily implemented screen switching thanks to singleton pattern */
		ScreenManager.getInstance().setManager(screen);
		ScreenManager.getInstance().show(screen);
		
	}


}
