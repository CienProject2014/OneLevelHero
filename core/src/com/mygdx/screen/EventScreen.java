package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.EventScene;

public class EventScreen implements Screen {
	OneLevelHero game;
	SpriteBatch batch;
	EventScene scene;
	Stage stage;
	Table table;
	String event;

	public EventScreen(OneLevelHero game) {
		this.game = game;
	}

	public EventScreen(OneLevelHero game, String event) {
		this.game = game;
		this.event = event;
		game.eventManager.setEvent(event);
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

		scene = new EventScene(table, batch);
		scene.setStage(stage);
		scene.load("Blackwood-scene-1");
		scene.start();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				scene.next();

				if (scene.isEnd) {
					// back to previous screen
					// that envoke this event screen

					// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
					game.setScreen(new VillageScreen(game));
				}

				return true;
			}
		});

		stage.addActor(table);
	}

	@Override
	public void hide() {
		Gdx.app.log("DEBUG", "EventScreen hide is called");
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
