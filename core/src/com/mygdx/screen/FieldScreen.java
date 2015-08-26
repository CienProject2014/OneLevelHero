package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class FieldScreen extends BaseScreen {

	private Stage gameUiStage, fieldStage, characterUiStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		fieldStage.draw();
		fieldStage.act();
		characterUiStage.draw();
		characterUiStage.act(delta);
		gameUiStage.draw();
		gameUiStage.act();
	}

	@Override
	public void show() {
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		fieldStage = stageFactory.makeStage(StageEnum.FIELD);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
		musicManager.setMovingMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		if (showLoadStage) {
			multiplexer.addProcessor(i++, loadPopupStage);
		} else {
			multiplexer.addProcessor(i++, fieldStage);
			multiplexer.addProcessor(i++, gameUiStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
