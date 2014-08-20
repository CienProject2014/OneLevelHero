package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;

public class CollectionScreen implements Screen {

	OneLevelHero game;
	Stage stage;
	TextButton endingButton;
	TextButton cgButton;
	TextButton bgmButton;
	TextButton backButton;
	TextButtonStyle textButtonStyle;
	BitmapFont font;

	public CollectionScreen(OneLevelHero game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(Assets.skin);

		endingButton = new TextButton("엔딩", Assets.skin);
		cgButton = new TextButton("CG", Assets.skin);
		bgmButton = new TextButton("BGM", Assets.skin);
		backButton = new TextButton("Back", Assets.skin);

		endingButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new EndingScreen(game));
			}
		});
		cgButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new CGScreen(game));
			}
		});
		bgmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new BGMScreen(game));
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
				game.setScreen(new MenuScreen());
			}
		});

		table.setFillParent(true);
		// table.debug();
		table.add(endingButton).expand().width(240).height(240).top().left();
		table.add(cgButton).width(240).height(240).top().right();
		table.row();
		table.add(bgmButton).width(240).height(240).bottom().left();
		table.add(backButton).bottom();
		table.row();

		stage.addActor(table);
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
