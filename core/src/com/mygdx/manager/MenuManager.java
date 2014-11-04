package com.mygdx.manager;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.stage.MenuStage;

public class MenuManager implements Manager{

	@Override
	public Stage setStage(String name) {
		
		return new MenuStage(name);
	}

}
