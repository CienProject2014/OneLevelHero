package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.FieldManager;

public class FieldScreen extends BaseScreen {
	@Autowired
	private FieldManager fieldManager;

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
		InputMultiplexer multiplexer = new InputMultiplexer();
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		fieldStage = stageFactory.makeStage(StageEnum.FIELD);

		multiplexer.addProcessor(0, fieldStage);
		multiplexer.addProcessor(1, gameUiStage);
		Gdx.input.setInputProcessor(multiplexer);
		musicManager.setMovingMusicAndPlay();
	}
}
