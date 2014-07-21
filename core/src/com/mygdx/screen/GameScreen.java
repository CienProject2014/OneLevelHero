package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Scene;

public class GameScreen implements Screen {
	
	OneLevelHero game;
	ImageButton optionButton;
	ImageButton minimapButton;
	ImageButton inventoryButton;
	TextButton statusButton1;
	TextButton statusButton2;
	TextButton statusButton3;
	TextButton battleButton; //test
	Image background;
	Stage stage;
	Scene scene;
	
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
		
		Viewport vp = stage.getViewport();
		
		Table uitable = new Table(Assets.skin);
		
		Assets.buttonload();
		optionButton = new ImageButton(Assets.ibuttond,Assets.ibuttonu);		
		minimapButton = new ImageButton(Assets.ibuttond,Assets.ibuttonu);
		inventoryButton = new ImageButton(Assets.ibuttond,Assets.ibuttonu);
		statusButton1 = new TextButton("status1",Assets.skin);
		statusButton2 = new TextButton("status2",Assets.skin);
		statusButton3 = new TextButton("status3",Assets.skin);		
		battleButton = new TextButton("Battle",Assets.skin); //test
		background = new Image(new Texture("prologue/scene1.jpg"));
		
		//버튼 동작
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
				Gdx.app.log("정보","inventoryPopUp창이 나타납니다.");
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
		
		battleButton.addListener(new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				// TODO Auto-generated method stub
				game.setScreen(new BattleScreen(game));
				Gdx.app.log("정보","전투가 시작됩니다");
			}			
		}); //test
		
		
		//테이블 설정
		uitable.setFillParent(true);
		uitable.add(minimapButton).expand().width(240).height(50).top().left();
		uitable.add(inventoryButton).width(240).height(50).top().left();		
		uitable.add(optionButton).width(240).height(50).top().left();
		uitable.add(battleButton).width(240).height(50).top().right();
		//uitable.row();	
		uitable.add(statusButton1).width(320).height(100).bottom().left();
		uitable.add(statusButton2).width(320).height(100).bottom().left();
		uitable.add(statusButton3).width(320).height(100).bottom().left();
		
		
		
		stage.addActor(uitable);
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
