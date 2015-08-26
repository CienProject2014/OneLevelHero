package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class CreditScreen extends BaseScreen {
	private Stage creditStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		creditStage.draw();
		creditStage.act(delta);
	}

	@Override
	public void show() {
		creditStage = stageFactory.makeStage(StageEnum.CREDIT);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, creditStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
