package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.MusicManager.MusicCondition;
import com.mygdx.state.Assets;

public class MenuScreen implements Screen {
	@Autowired
	private Assets assets;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private StageFactory stageFactory;
	private Stage stage;

	public MenuScreen() {
		Gdx.app.debug("MenuScreen", "Constructor() call");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		musicManager.setMusicAndPlay(assets.musicMap.get("opening"),
				assets.musicVolume, MusicCondition.IF_IS_NOT_PLAYING);
		stage = stageFactory.makeStage(StageEnum.MENU);

		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void hide() {

		//		stage.dispose();

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

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}

	public void setMusicManager(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

	public StageFactory getStageFactory() {
		return stageFactory;
	}

	public void setStageFactory(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

}
