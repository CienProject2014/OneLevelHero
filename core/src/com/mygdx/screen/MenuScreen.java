package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.MusicManager.MusicCondition;

public class MenuScreen extends RootScreen {
	private Stage stage;

	public MenuScreen() {
		Gdx.app.debug("MenuScreen", "Constructor() call");
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		stage.draw();
	}

	@Override
	public void show() {
		musicManager.setMusicAndPlay(assets.musicMap.get("opening"),
				assets.musicVolume, MusicCondition.IF_IS_NOT_PLAYING);
		stage = stageFactory.makeStage(StageEnum.MENU);

		Gdx.input.setInputProcessor(stage);
	}
}
