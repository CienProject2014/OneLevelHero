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
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.ChatScene;
import com.mygdx.game.OneLevelHero;
import com.mygdx.util.ScreenManager;

public class EventScreen implements Screen {
	private OneLevelHero game = ScreenManager.getGame();
	private SpriteBatch batch;
	private ChatScene chatScene;
	private Stage stage;
	private Table table;
	private String event;

	public EventScreen() {

	}

	public EventScreen(OneLevelHero game, String event) {
		this.game = game;
		this.setEvent(event);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		chatScene.show(delta); // 배경 출력
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
				if (chatScene.isNext()) {
					chatScene.showNextScene();
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
		//인스턴스 생성
		chatScene = new ChatScene(table, batch, game.eventManager.getEventCode(), stage);
		// 파싱을 하기 위한 로드, 씬을 뿌려줌
		chatScene.load();

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
