package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;

public class GameScreen implements Screen {
	
	OneLevelHero game;
	TextButton optionButton;
	TextButton minimapButton;
	TextButton inventoryButton;
	Label location;
	TextButton statusButton1;
	TextButton statusButton2;
	TextButton statusButton3;
	Stage stage;
	
	
	public GameScreen(OneLevelHero game){
		this.game = game;
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
		
		optionButton = new TextButton("option",Assets.skin);		
		minimapButton = new TextButton("minimap",Assets.skin);
		inventoryButton = new TextButton("invenroy",Assets.skin);
		location = new Label("Hello", Assets.skin);
		statusButton1 = new TextButton("status1",Assets.skin);
		statusButton2 = new TextButton("status2",Assets.skin);
		statusButton3 = new TextButton("status3",Assets.skin);
		
		statusButton1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("정보","Status1의 상태가 나타납니다.");
			}
		});
		statusButton2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("정보","Status2의 상태가 나타납니다.");
			}
		});
		statusButton3.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub				
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("정보","Status3의 상태가 나타납니다.");
			}
		});
		inventoryButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.log("정보","invenrotyPopUp창이 나타납니다.");
			}			
		});
		optionButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.log("정보","OptionScreen이 나타납니다.");
			}			
		});
		minimapButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.log("정보","minimap창이 나타납니다.");
			}			
		});
		table.setFillParent(true);
		table.add(minimapButton).expand().width(240).height(50).top().left();
		table.add(inventoryButton).width(240).height(50).top();
		table.add(optionButton).width(240).height(50).top().right();
		table.row();
		table.add(statusButton1).width(300).height(100).bottom();
		table.add(statusButton2).width(300).height(100).bottom();
		table.add(statusButton3).width(300).height(100).bottom();
		
		
		stage.addActor(table);
		//stage.addActor(backButton);
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
