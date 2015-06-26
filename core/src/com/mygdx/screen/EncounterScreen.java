package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class EncounterScreen extends RootScreen {
	private Stage encountStage;
	private Stage monsterStage;

	public EncounterScreen() {}

	@Override
	public void show() {
		encountStage = stageFactory.makeStage(StageEnum.ENCOUNTER);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);

		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// 나중에 부르는 걸 위에 그림.
		monsterStage.draw();
		encountStage.draw();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(encountStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
