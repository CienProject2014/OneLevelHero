package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
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
	
	Image logo;
	
	BitmapFont font;

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

		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(Assets.skin);
		
		Assets.load();
		Assets.buttonload();
		
		//logo.setDrawable(Assets.logo);
		
		startButton = new TextButton("새로운 시작", Assets.skin);
		optionsButton = new TextButton("옵션", Assets.skin);
		creditButton = new TextButton("크레딧", Assets.skin);
		collectionButton = new TextButton("콜렉션", Assets.skin);
		exitButton = new TextButton("종료", Assets.skin);
		
		Viewport vp = stage.getViewport();
		
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
		
		float heighttest = 0.38f;
		float widthtest = 0.2f;
		
		System.out.println(heighttest * vp.getViewportHeight());
		
		int realheight = (int) (heighttest * vp.getViewportHeight());
		int realwidth = (int) (widthtest * vp.getViewportWidth());
		
		Assets.logo.setHeight((int)(0.4f*vp.getViewportHeight()));
		Assets.logo.setWidth((int)(0.6f*vp.getViewportWidth()));
		
		table.setFillParent(true);
//		table.debug(); 
		table.add(startButton).expand().width(realwidth).height(realheight).top().left();
		//table.add(Assets.logo).center();
		table.add(optionsButton).width(realwidth).height(realheight).top().right();
		table.row();
		table.add(creditButton).width(realwidth).height(realheight).bottom().left();
		table.add(collectionButton).width(realwidth).height(realheight).bottom().right();
		//table.add(exitButton).width(realheight).height(realheight).bottom().right();
		table.row();
		
		Assets.logo.setPosition((int)(0.2f*vp.getViewportWidth()), (int)(0.3f*vp.getViewportHeight()));
		
		stage.addActor(Assets.logo);
		
		stage.addActor(table);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		System.out.println("hide");
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		System.out.println("pause");
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		System.out.println("resume");
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("dispose");
		
	}
	
}
