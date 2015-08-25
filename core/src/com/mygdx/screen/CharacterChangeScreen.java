package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class CharacterChangeScreen extends BaseScreen {
	private Stage characterChangeStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		characterChangeStage.draw();
		characterChangeStage.act(delta);
	}

	@Override
	public void show() {
		characterChangeStage = stageFactory.makeStage(StageEnum.CHARACTER_CHANGE);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, characterChangeStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
