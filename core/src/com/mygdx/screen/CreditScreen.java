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
import com.mygdx.event.CreditScene;
import com.mygdx.resource.Assets;
import com.mygdx.util.EventManager;

public class CreditScreen implements Screen {
	SpriteBatch batch;
	CreditScene creditScene;
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
		creditScene.show(delta); // 배경 출력
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
		table = new Table(Assets.skin);
		table.setFillParent(true);
		table.row();

		showEventScene();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (creditScene.isNext()) {
					creditScene.showNextScene();
				} else {
					// back to previous screen
					// that envoke this event screen

					// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
					new ScreenController(ScreenEnum.MENU);

				}
				return true;
			}
		});

		stage.addActor(table); // stage에 table액터 추가하기
	}

	private void showEventScene() {

		EventManager.getInstance().setEventCode("Credit-scene-1");
		creditScene
				.settingBeforeLoad(EventManager.getInstance().getEventCode());

		// 파싱을 하기 위한 로드
		creditScene.load();
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
