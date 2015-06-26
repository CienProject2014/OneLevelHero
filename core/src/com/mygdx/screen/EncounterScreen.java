package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;

public class EncounterScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	private Stage encountStage;
	private Stage monsterStage;

	public EncounterScreen() {
	}

	@Override
	public void show() {
		encountStage = stageFactory.makeStage(StageEnum.ENCOUNTER);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);

		setInputProcessor();
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// 나중에 부르는 걸 위에 그림.
		monsterStage.draw();
		encountStage.draw();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(encountStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
