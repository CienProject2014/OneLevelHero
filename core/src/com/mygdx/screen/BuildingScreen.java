package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BuildingScreen extends BaseScreen {
	public static boolean isInSave;
	private Stage buildingStage;
	private Stage gameUiStage;
	private Stage saveStage;
	private Stage loadStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		buildingStage.draw();
		gameUiStage.draw();
		if (isInSave) {
			saveStage.draw();
		}
	}

	@Override
	public void show() {
		buildingStage = stageFactory.makeStage(StageEnum.BUILDING);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		saveStage = stageFactory.makeStage(StageEnum.SAVE);
		loadStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
		musicManager.setWorldNodeMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		if (isInSave) {
			multiplexer.addProcessor(i++, saveStage);
			multiplexer.addProcessor(i++, gameUiStage);
			multiplexer.addProcessor(i++, buildingStage);
		} else {
			multiplexer.addProcessor(i++, gameUiStage);
			multiplexer.addProcessor(i++, buildingStage);
			multiplexer.addProcessor(i++, saveStage);
		}
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadStage);
		}
		Gdx.input.setInputProcessor(multiplexer);

	}
}
