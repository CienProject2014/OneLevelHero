package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.EventScene;
import com.mygdx.stage.WorldStage;

public class WorldMapScreen implements Screen {

	private OneLevelHero game;
	private SpriteBatch batch;
	private EventScene scene;
	private WorldStage worldmap;

	public WorldMapScreen() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// stage.draw();
		worldmap.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

		worldmap = new WorldStage();
		Gdx.input.setInputProcessor(worldmap);
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

	public OneLevelHero getGame() {
		return game;
	}

	public void setGame(OneLevelHero game) {
		this.game = game;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public EventScene getScene() {
		return scene;
	}

	public void setScene(EventScene scene) {
		this.scene = scene;
	}

}
