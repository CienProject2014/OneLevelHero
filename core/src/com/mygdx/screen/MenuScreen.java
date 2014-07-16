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
import com.mygdx.screen.OptionScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.CollectionScreen;

public class MenuScreen implements Screen {

	OneLevelHero game;
	Stage stage;
	TextButton startButton;
	TextButton optionsButton;
	TextButton creditButton;
	TextButton exitButton;
	TextButton collectionButton;
	TextButtonStyle textButtonStyle;
	BitmapFont font;

	//Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/Test.mp3"));

	public MenuScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//System.out.println("Menu");
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

		//long id = sound.play(1.0f);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(Assets.skin);
		
		startButton = new TextButton("새로운 시작", Assets.skin);
		optionsButton = new TextButton("옵션", Assets.skin);
		creditButton = new TextButton("크레딧", Assets.skin);
		collectionButton = new TextButton("콜렉션", Assets.skin);
		exitButton = new TextButton("종료", Assets.skin);
				
		startButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				game.setScreen(new LoadScreen(game));
			}
		});
		optionsButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new OptionScreen(game));
			}
		});
		creditButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new CreditScreen(game));
			}
		});
		collectionButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new CollectionScreen(game));
			}
		});
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new TestScreen(game));
			}
		});
		
		table.setFillParent(true);
//		table.debug(); 
		table.add(startButton).expand().width(240).height(240).top().left();
		table.add(optionsButton).width(240).height(240).top().right();
		table.row();
		table.add(creditButton).width(240).height(240).bottom().left();
		table.add(collectionButton).width(240).height(240).bottom();
		table.add(exitButton).width(240).height(240).bottom().right();
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
