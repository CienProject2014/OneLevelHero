package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BuildingScreen extends BaseScreen {
	private Stage buildingStage;
	private Stage gameUiStage;

	@Override
	public void render(float delta) {
		super.render(delta);

		buildingStage.draw();
		gameUiStage.draw();
	}

	@Override
	public void show() {
		buildingStage = stageFactory.makeStage(StageEnum.BUILDING);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, buildingStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
