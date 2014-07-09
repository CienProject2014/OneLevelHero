package com.mygdx.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;

public class GameScreen implements Screen {
	OneLevelHero game;
	Stage stage;
	TextButton backButton;
	
	public GameScreen(OneLevelHero game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("Game");
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
		
		backButton = new TextButton("Back", Assets.skin);
		
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
		table.add(backButton).expandX().padLeft(10);
		table.left().bottom();
		
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
