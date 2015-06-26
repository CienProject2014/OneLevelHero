package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class StatusScreen extends RootScreen {
	private Stage statusStage;

	@Override
	public void render(float delta) {
		super.render(delta);

		statusStage.draw();
	}

	@Override
	public void show() {
		statusStage = stageFactory.makeStage(StageEnum.STATUS);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, statusStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
