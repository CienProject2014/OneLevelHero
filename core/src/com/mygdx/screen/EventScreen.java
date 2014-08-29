package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.ChatScene;
import com.mygdx.event.Scene;
import com.mygdx.event.SelectScene;
import com.mygdx.util.EventManager;

public class EventScreen implements Screen {
	EventTypeEnum eventType;
	private SpriteBatch batch;
	private Scene scene;
	private Stage stage;
	private Table table;
	private String event;

	public EventScreen() {

	}

	public EventScreen(String event) {
		this.event = event;
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
		stage = new Stage();
		batch = new SpriteBatch();
		table = new Table();
		table.setFillParent(true);

		showEventScene();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (scene.isNext()) {
					scene.showNextScene();
				} else {
					// back to previous screen
					// that envoke this event screen

					// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
					new ScreenController(ScreenEnum.VILLAGE);

				}
				return true;
			}
		});

		stage.addActor(table);
	}

	private void showEventScene() {
		// 인스턴스 생성
		eventType = EventManager.getInstance().getEventType();

		switch (eventType) {
			case CHAT:
				scene = new ChatScene(table, batch, EventManager.getInstance().getEventCode());
				break;
			case SELECT:
				scene = new SelectScene(table, batch, EventManager.getInstance().getEventCode());
				break;
			default:
				scene = new ChatScene(table, batch, EventManager.getInstance().getEventCode());
				Gdx.app.log("error", "scene할당 에러");
				break;
		}
		// 파싱을 하기 위한 로드

		scene.load();

	}

	@Override
	public void hide() {
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
