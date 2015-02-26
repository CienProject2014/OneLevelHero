package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.inputProcessor.MapInputProcessor;

public class WorldMapScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	private Stage worldMapStage;
	private InputMultiplexer multiplexer;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldMapStage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		worldMapStage = stageFactory.makeStage(StageEnum.WORLD_MAP);
		InputProcessor MapInputProcessor = new MapInputProcessor(worldMapStage);
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, worldMapStage);
		multiplexer.addProcessor(1, MapInputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
