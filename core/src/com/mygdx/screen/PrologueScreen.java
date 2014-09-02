/*
 * Texture 생성자 안에 파일의 경로를 적을때 Gdx.files.internal 안붙이고
 * 그냥 파일 이름을 String으로 써주면 됨
 */
package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.util.EventManager;
import com.mygdx.util.SceneManager;

public class PrologueScreen implements Screen {

	private Stage stage;
	private SceneManager scene;

	public PrologueScreen() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		scene = new SceneManager(EventManager.getInstance().getEventKey());
		stage = scene.getSceneUi();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (scene.isNext()) {
					scene.showNextScene();
					stage = scene.getSceneUi();
				} else {

					// back to previous screen
					// that envoke this event screen

					EventManager.getInstance().setEventCode("Prg-scene-2", EventTypeEnum.CHAT);

					new ScreenController(ScreenEnum.EVENT);

				}

				return true;
			}
		});

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	}

}
