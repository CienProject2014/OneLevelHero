package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class EncounterScreen extends BaseScreen {
	private Stage encountStage;

	public EncounterScreen() {
	}

	@Override
	public void show() {
		encountStage = stageFactory.makeStage(StageEnum.ENCOUNTER);

		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// 나중에 부르는 걸 위에 그림.
		encountStage.draw();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(encountStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
