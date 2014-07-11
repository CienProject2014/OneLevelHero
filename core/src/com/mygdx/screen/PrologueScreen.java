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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Scripts;

public class PrologueScreen implements Screen{
	OneLevelHero game;
	Stage stage;
	SpriteBatch batch;
	Texture img;
	Image[] image;
	Scripts script;
	Label textlabel;
	
	public PrologueScreen(OneLevelHero game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.app.log("Prologue Message", "Prologue");
		
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
		batch = new SpriteBatch();
		
		script = new Scripts(1);
		
		String text = script.ScriptGetter("Prologue-scene1");
		
		textlabel = new Label(text, Assets.skin);
		
		System.out.println(text);
		
		image = new Image[4];
		image[0] = new Image(new Texture(Gdx.files.internal("prologue/s#1.png")));
		image[1] = new Image(new Texture(Gdx.files.internal("prologue/s#2.jpg")));
		image[2] = new Image(new Texture(Gdx.files.internal("prologue/s#3.jpg")));
		image[3] = new Image(new Texture(Gdx.files.internal("prologue/s#4.jpg")));
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(image[0]);	// 처음 이미지
		stage.addActor(textlabel);
		
		image[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addActor(image[1]);
				textlabel.setText(script.ScriptGetter("Prologue-scene2"));
				stage.addActor(textlabel);

				return true;
			}
		});
		image[1].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addActor(image[2]);
				textlabel.setText(script.ScriptGetter("Prologue-scene3"));
				stage.addActor(textlabel);
				return true;
			}
		});
		image[2].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addActor(image[3]);
				textlabel.setText(script.ScriptGetter("Prologue-scene4"));
				stage.addActor(textlabel);
				return true;
			}
		});
		image[3].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen(game));
				return true;
			}
		});
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
