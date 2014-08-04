package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.OneLevelHero;
import com.mygdx.stage.VillageStage;

public class VillageScreen implements Screen {

	OneLevelHero game;
	Image background;
	SpriteBatch batch;
	String villagename;

	VillageStage vs1;
	VillageStage vs2;

	int key = 2;

	boolean state = true;

	public VillageScreen(OneLevelHero game) {
		this.game = game;
	}

	public VillageScreen(OneLevelHero game, String villagename) {
		this.game = game;
		this.villagename = villagename;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		if (state == true) {

			batch.draw(vs1.background, 0, 0, vs1.getWidth(), vs1.getHeight());

		}
		if (state == false) {

			batch.draw(vs2.background, 0, 0, vs2.getWidth(), vs2.getHeight());

		}

		batch.end();

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
		batch = new SpriteBatch();

		vs1 = new VillageStage(villagename + "-0", game);
		Gdx.input.setInputProcessor(vs1);
		vs2 = new VillageStage(villagename + "-1", game);

		ChangeListener sift = new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				if (state == true)
					state = false;
				else if (state == false)
					state = true;
			}

		};

		vs1.sift_button.addListener(sift);
		vs2.sift_button.addListener(sift);
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
		vs1.dispose();
		vs2.dispose();
	}

}
