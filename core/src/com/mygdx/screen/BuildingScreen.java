package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.stage.SaveStage;

public class BuildingScreen extends BaseScreen {
	private Stage buildingStage;
	private Stage gameUiStage;
	private Stage saveStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		buildingStage.draw();
		gameUiStage.draw();
		if (SaveStage.isTouched) {
			saveStage.draw();
		}
	}

	@Override
	public void show() {
		saveStage = stageFactory.makeStage(StageEnum.SAVE);
		buildingStage = stageFactory.makeStage(StageEnum.BUILDING);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (SaveStage.isTouched) {
			multiplexer.addProcessor(0, saveStage);
			multiplexer.addProcessor(1, gameUiStage);
			multiplexer.addProcessor(2, buildingStage);
		} else {
			multiplexer.addProcessor(0, gameUiStage);
			multiplexer.addProcessor(1, buildingStage);
			multiplexer.addProcessor(2, saveStage);
		}
		Gdx.input.setInputProcessor(multiplexer);

	}
}
