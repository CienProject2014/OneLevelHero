package com.mygdx.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

// 충돌 문제로 관련 리소스를 삭제했기떄문에 더 이상 동작하지 않음 소스만 참조할 것
public class Overlap2DTest implements Screen {
	private GameStage stage;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		stage = new GameStage();
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
}
