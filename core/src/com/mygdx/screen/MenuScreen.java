package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;

public class MenuScreen implements Screen {

	OneLevelHero game;
	Stage stage;
	ImageButton startButton;
	ImageButton optionsButton;
	ImageButton creditButton;
	TextButton exitButton;
	ImageButton extraButton;
	TextButtonStyle textButtonStyle;
	Image logo;
	SpriteBatch batch;
	Sprite sprite;
	BitmapFont font;

	private static Music music = Gdx.audio.newMusic(Gdx.files.internal("data/Test.mp3"));

	public MenuScreen(OneLevelHero game) {
		this.game = game;
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
		getMusic().setVolume(Assets.musicVolume);
		getMusic().play();
		Texture texture = new Texture(Gdx.files.internal("texture/MainMenu_Background.png"));
		Image background = new Image(texture);

		stage = new Stage();
		Assets.loadSize(stage);
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(Assets.skin);

		Assets.menuScreenLoad();

		startButton = new ImageButton(Assets.start_before, Assets.start_after);
		optionsButton = new ImageButton(Assets.option_before, Assets.option_after);
		creditButton = new ImageButton(Assets.credit_before, Assets.credit_after);
		extraButton = new ImageButton(Assets.extra_before, Assets.extra_after);
		exitButton = new TextButton("종료", Assets.skin);

		startButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new LoadScreen(game));
				music.dispose();
			}
		});
		optionsButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new OptionScreen(game));
				music.dispose();
			}
		});
		creditButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new CreditScreen(game));
				music.dispose();

			}
		});
		extraButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new CollectionScreen(game));
				music.dispose();

			}
		});
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new TestScreen(game));
				music.dispose();
			}
		});

		int realheight = (int) (Assets.realHeight);
		int realwidth = (int) (Assets.realWidth);

		Assets.logo.setHeight((int) (0.4f * Assets.realHeight));
		Assets.logo.setWidth((int) (0.6f * Assets.realWidth));
		table.setFillParent(true);

		table.add(extraButton).height(0.35f * realheight).width(0.3f * realwidth).expand().top().left();
		table.add(creditButton).height(0.35f * realheight).width(0.3f * realwidth).top().right();
		table.row();
		table.add(startButton).height(0.35f * realheight).width(0.3f * realwidth).bottom().left();
		table.add(optionsButton).height(0.35f * realheight).width(0.3f * realwidth).bottom().right();

		table.debug();
		table.debugTable();

		Assets.logo.setPosition((int) (0.2f * Assets.realWidth), (int) (0.3f * Assets.realHeight));

		background.setSize(realwidth, realheight);

		stage.addActor(background);
		stage.addActor(Assets.logo);

		stage.addActor(table);
	}

	@Override
	public void hide() {

		stage.dispose();

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

	public static Music getMusic() {
		return music;
	}

	public static void setMusic(Music music) {
		MenuScreen.music = music;
	}

}
