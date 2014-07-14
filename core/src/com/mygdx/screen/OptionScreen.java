package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.OneLevelHero;
import com.mygdx.popup.SoundPopup;
import com.mygdx.resource.Assets;

class OptionScreen implements Screen{
	
	OneLevelHero game;
	Stage stage;
	TextButton soundButton;
	TextButton savedataButton;
	TextButton bonusPointButton;
	TextButton backButton;
	
	
	public OptionScreen(OneLevelHero game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("Option");
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
		soundButton = new TextButton("Sound", Assets.skin);
		savedataButton = new TextButton("SaveData", Assets.skin);
		bonusPointButton = new TextButton("BonusPoint", Assets.skin);
		backButton = new TextButton("Back", Assets.skin);
		
		
		soundButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				stage.addActor(new SoundPopup("SoundSetting"));
			}			
		});
		
		savedataButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SaveScreen(game));
			}
		});
		bonusPointButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new BonusPointScreen(game));
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
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MenuScreen(game));
			}
		});
		
		table.setFillParent(true);
		table.add(soundButton).expand().width(240).height(240).top().left();
		table.add(savedataButton).width(240).height(240).top().right();
		table.row();
		table.add(bonusPointButton).width(240).height(240).bottom().left();
		table.add(backButton).width(240).height(240).bottom().right();
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
