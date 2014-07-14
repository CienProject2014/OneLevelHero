package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Scripts;

public class PrologueScreen implements Screen{
	OneLevelHero game;
	Stage stage;	
	Texture img;
	Image[] image;
	Scripts script;
	Label textlabel;
	TextureRegion region;
	Texture texture;
	
	public PrologueScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.app.log("Prologue Message", "Prologue");
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		script = new Scripts(1);
		image = new Image[4];
		
		Gdx.input.setInputProcessor(stage);

		image[0] = new Image(new Texture(Gdx.files.internal("prologue/scene1.jpg")));
		image[1] = new Image(new Texture(Gdx.files.internal("prologue/scene2.jpg")));
		image[2] = new Image(new Texture(Gdx.files.internal("prologue/scene3.jpg")));
		image[3] = new Image(new Texture(Gdx.files.internal("prologue/scene4.jpg")));

		image[0].addAction( Actions.sequence( Actions.fadeOut( 0f ), Actions.fadeIn( 0.5f ) ) );
		image[1].addAction( Actions.sequence( Actions.fadeOut( 0f ), Actions.fadeIn( 0.5f ) ) );
		image[2].addAction( Actions.sequence( Actions.fadeOut( 0f ), Actions.fadeIn( 0.5f ) ) );
		image[3].addAction( Actions.sequence( Actions.fadeOut( 0f ), Actions.fadeIn( 0.5f ) ) );
		
		String text = script.ScriptGetter("Prologue-scene1");
		
		textlabel = new Label(text, Assets.skin);
		
		stage.addActor(image[0]);
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
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
