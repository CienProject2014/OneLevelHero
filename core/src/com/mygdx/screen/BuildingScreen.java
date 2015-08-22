package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BuildingScreen extends BaseScreen {
	public static boolean isTouched = false;
	private Stage buildingStage;
	private Stage gameUiStage;
	private Stage saveStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		buildingStage.draw();
		gameUiStage.draw();
		if (isTouched) {
			saveStage.draw();
		}
	}

	@Override
	public void show() {
		buildingStage = stageFactory.makeStage(StageEnum.BUILDING);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		saveStage = stageFactory.makeStage(StageEnum.SAVE);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (isTouched) {
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
