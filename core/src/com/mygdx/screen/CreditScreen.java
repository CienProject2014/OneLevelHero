package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.Scene;

public class CreditScreen implements Screen {
	SpriteBatch batch;
	Scene scene;
	TextButton backButton;
	Table table;
	Stage stage;
	String event;

	public CreditScreen(String event) {
		this.event = event;
	}

	public CreditScreen() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		scene.show(delta); // 배경 출력
		batch.end();

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		showEventScene();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (scene.isNext()) {
					scene.showNextScene();
				} else {
					// back to previous screen
					// that envoke this event screen

					// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
					new ScreenController(ScreenEnum.MENU);

				}
				return true;
			}
		});
	}

	private void showEventScene() {
		/*
				EventManager.getInstance().setEventCode("Crd-scene-1");
				EventManager.getInstance().setEventType(EventTypeEnum.CREDIT);

				if (EventManager.getInstance().getEventType() == EventTypeEnum.CREDIT)
					scene = new CreditScene(stage, batch, EventManager.getInstance()
							.getEventCode());
				else
					Gdx.app.log("Error", "Scene 주입 에러");
				// 파싱을 하기 위한 로드
				scene.load();
				*/
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
