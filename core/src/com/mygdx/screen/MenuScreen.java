package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.MusicAssets;
import com.mygdx.currentState.SoundInfo;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.MusicManager.MusicCondition;

public class MenuScreen extends BaseScreen {
	@Autowired
	private MusicAssets musicAssets;
	@Autowired
	private SoundInfo soundInfo;
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
		musicManager.setMusicAndPlay(musicAssets.getMusic("opening"),
				soundInfo.getMusicVolume(), MusicCondition.IF_IS_NOT_PLAYING);
		stage = stageFactory.makeStage(StageEnum.MENU);

		Gdx.input.setInputProcessor(stage);
	}

	public MusicAssets getMusicAssets() {
		return musicAssets;
	}

	public void setMusicAssets(MusicAssets musicAssets) {
		this.musicAssets = musicAssets;
	}

	public SoundInfo getSoundInfo() {
		return soundInfo;
	}

	public void setSoundInfo(SoundInfo soundInfo) {
		this.soundInfo = soundInfo;
	}

}
