package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.MovingController;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;

public class MovingScreen implements Screen {

	OneLevelHero game;
	Stage stage;
	TextButton goButton;
	TextButton backButton;
	TextButton rightButton;
	TextButton leftButton;
	Label pointLabel;
	Table table;
	SpriteBatch batch;
	Texture texture = new Texture(Gdx.files.internal("data/justground.jpg"));

	MovingController controller;

	String presentVil;
	String targetVil;

	public MovingScreen(OneLevelHero game) {
		this.game = game;
		controller = new MovingController();

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		batch.begin();
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(texture, 0, 0);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		batch = new SpriteBatch();
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		goButton = new TextButton("Go", Assets.skin);
		backButton = new TextButton("Back", Assets.skin);
		rightButton = new TextButton("Right", Assets.skin);
		leftButton = new TextButton("Left", Assets.skin);
		pointLabel = new Label("Point", Assets.skin);

		Gdx.input.setInputProcessor(stage);

		goButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				goForward();
			}
		});

		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				goBackward();
			}
		});

		rightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				goRightward();
			}
		});

		leftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				goLeftward();
			}
		});

		controller.checkStage();

		table.add(goButton).expand().top().padTop(20);
		table.row();
		table.add(backButton).bottom().padBottom(20);
		stage.addActor(table);

	}

	public void goForward() {
		Gdx.app.log("test", "goForward");

	}

	public void goBackward() {
		Gdx.app.log("test", "goBackward");
	}

	public void goLeftward() {
		Gdx.app.log("test", "goLeftward");
	}

	public void goRightward() {
		Gdx.app.log("test", "goRightward");
	}

	public static void setController() {

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
