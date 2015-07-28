package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class FieldScreen extends BaseScreen {

	//
	private Stage gameUiStage, fieldStage;

	@Override
	public void render(float delta) {
		super.render(delta);

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
		/*
		 * movingLabel = new Label("Point", uiComponentAssets.getSkin());
		 * movingLabel.setColor(0, 0, 0, 1);
		 */
		InputMultiplexer multiplexer = new InputMultiplexer();
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		fieldStage = stageFactory.makeStage(StageEnum.FIELD);

		multiplexer.addProcessor(0, fieldStage);
		multiplexer.addProcessor(1, gameUiStage);
		Gdx.input.setInputProcessor(multiplexer);
		musicManager.setMovingMusicAndPlay();
	}
}
