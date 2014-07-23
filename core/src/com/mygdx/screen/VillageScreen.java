package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Scene;
import com.mygdx.stagemanager.VillageStage;

public class VillageScreen implements Screen {

	OneLevelHero game;
	ImageButton optionButton;
	ImageButton minimapButton;
	ImageButton inventoryButton;
	TextButton statusButton1;
	TextButton statusButton2;
	TextButton statusButton3;
	Image background;
	Scene scene;

	VillageStage vs1;
	VillageStage vs2;

	int key = 2;

	boolean state = true;

	public VillageScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		System.out.println(state);
		if (state == true) {
			vs2.unfocusAll();
			Gdx.input.setInputProcessor(vs1);
			vs1.draw();
		}
		if (state == false) {
			vs1.unfocusAll();
			Gdx.input.setInputProcessor(vs2);
			vs2.draw();
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		vs1 = new VillageStage("Blackwood-0");
		Gdx.input.setInputProcessor(vs1);
		vs2 = new VillageStage("Blackwood-1");

		InputListener sift = new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (state == true)
					state = false;
				else if (state == false)
					state = true;
			}
		};

		ChangeListener sift2 = new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				if (state == true)
					state = false;
				else if (state == false)
					state = true;

			}

		};

		vs1.sift_button.addListener(sift2);
		vs2.sift_button.addListener(sift2);

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
