package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.inputProcessor.MapInputProcessor;

public class WorldMapScreen extends BaseScreen {
	private Stage worldMapStage;
	private Stage gameUiStage;
	private InputMultiplexer multiplexer;

	@Override
	public void render(float delta) {
		super.render(delta);
		worldMapStage.draw();
		gameUiStage.draw();
	}

	@Override
	public void show() {
		worldMapStage = stageFactory.makeStage(StageEnum.WORLD_MAP);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputProcessor MapInputProcessor = new MapInputProcessor(worldMapStage);
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, worldMapStage);
		multiplexer.addProcessor(2, MapInputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
