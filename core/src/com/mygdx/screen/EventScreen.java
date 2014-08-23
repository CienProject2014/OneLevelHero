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
	OneLevelHero game = ScreenManager.getGame();
	SpriteBatch batch;
	ChatScene chatScene;
	Stage stage;
	Table table;
	String event;

	public EventScreen() {

	}

	public EventScreen(OneLevelHero game, String event) {
		this.game = game;
		this.event = event;
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
		chatScene = new ChatScene(table, batch);
		//스테이지 주입하기
		chatScene.setStage(stage);
		//로드전 세팅
		chatScene.settingBeforeLoad(game.eventManager.getEventCode());
		// 파싱을 하기 위한 로드
		chatScene.load();
		// 씬 뿌려주기
		chatScene.showView();
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
}
