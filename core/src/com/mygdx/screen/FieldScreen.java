package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class FieldScreen extends BaseScreen {

	private Stage gameUiStage, fieldStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		fieldStage.draw();
		fieldStage.act();
		gameUiStage.draw();
		gameUiStage.act();
		/*
		 * movingLabel.setText(movingInfo.getDestinationNode() + "까지" +
		 * movingInfo.getLeftRoadLength());
		 */
	}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		fieldStage = stageFactory.makeStage(StageEnum.FIELD);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
		musicManager.setMovingMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadPopupStage);
		} else {
			multiplexer.addProcessor(0, fieldStage);
			multiplexer.addProcessor(1, gameUiStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
