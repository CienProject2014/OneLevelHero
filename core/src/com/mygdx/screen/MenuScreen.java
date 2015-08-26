package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.MusicAssets;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.MusicManager.MusicCondition;

public class MenuScreen extends BaseScreen {
	@Autowired
	private MusicAssets musicAssets;
	private Stage stage;

	public MenuScreen() {
		Gdx.app.debug("MenuScreen", "Constructor() call");
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		stage.draw();
		if (showLoadStage) {
			loadPopupStage.draw();
		}
	}

	@Override
	public void show() {
		musicManager.setMusicAndPlay(musicAssets.getMusic("bgm_title"), MusicCondition.WHENEVER);
		stage = stageFactory.makeStage(StageEnum.MENU);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadPopupStage);
		} else {
			multiplexer.addProcessor(0, stage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
