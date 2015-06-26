package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;

public class BuildingScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	private Stage buildingStage;
	private Stage gameUiStage;

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		buildingStage.draw();
		gameUiStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public StageFactory getStageFactory() {
		return stageFactory;
	}

	public void setStageFactory(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

}
