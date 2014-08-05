package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.GameUi;
import com.mygdx.resource.PrologueScene;

public class GameScreen extends GameUi implements Screen {

	OneLevelHero game;
	ImageButton optionButton;
	ImageButton minimapButton;
	ImageButton inventoryButton;
	TextButton statusButton1;
	TextButton statusButton2;
	TextButton statusButton3;
	TextButton battleButton; // test
	Image background;
	Stage stage;
	PrologueScene scene;

	public GameScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();

		GameUi ui = new GameUi();

		Gdx.input.setInputProcessor(stage);

		ui.addGameUi();
		stage.addActor(ui);

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
