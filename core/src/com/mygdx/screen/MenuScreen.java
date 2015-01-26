package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.SoundManager;
import com.mygdx.state.Assets;

@Component
@Scope(value = "prototype")
public class MenuScreen implements Screen {
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private StageFactory stageFactory;
	private Stage stage;

	private static Music music;

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
		setMusic(Assets.mainMusic);
		getMusic().setVolume(Assets.musicVolume);
		soundManager.playMusic(getMusic());

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

	public static Music getMusic() {
		return music;
	}

	public static void setMusic(Music music) {
		MenuScreen.music = music;
	}

}
