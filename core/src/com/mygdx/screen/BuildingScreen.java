package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.MusicEnum;
import com.mygdx.enums.StageEnum;

public class BuildingScreen extends BaseScreen {
	public static boolean isInSave;
	public static boolean isClickPopup;
	private Stage buildingStage;
	private Stage gameUiStage;
	private Stage saveStage;
	private Stage loadStage;
	private Stage gameObjectPopupStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		buildingStage.draw();
		gameUiStage.draw();
		buildingStage.getCamera().update();
		gameUiStage.act(delta);
		if (isInSave) {
			saveStage.draw();
		}
		if (showLoadStage) {
			loadStage.draw();
		}
		if (isClickPopup) {
			gameObjectPopupStage.draw();
		}

	}

	@Override
	public void show() {
		gameObjectPopupStage = stageFactory.makeStage(StageEnum.BUILDING_REST_POPUP);
		buildingStage = stageFactory.makeStage(StageEnum.BUILDING);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		saveStage = stageFactory.makeStage(StageEnum.SAVE);
		loadStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
		musicManager.setMusicAndPlay(MusicEnum.WORLD_NODE_MUSIC);
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
		if (isClickPopup) {
			multiplexer.addProcessor(0, gameObjectPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);

	}
}
