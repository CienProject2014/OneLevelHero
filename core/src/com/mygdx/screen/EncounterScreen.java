package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.factory.StageFactory;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.MonsterStage;

public class EncounterScreen implements Screen {
	EncounterStage encountStage;
	MonsterStage monsterStage;
	
	public EncounterScreen() {}
	
	@Override
	public void show() {
		encountStage = (EncounterStage) StageFactory.getInstance().makeStage("encount");
		monsterStage = (MonsterStage) StageFactory.getInstance().makeStage("monster");
		
		setInputProcessor();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
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

}
