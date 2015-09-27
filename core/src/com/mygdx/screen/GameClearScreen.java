package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.SoundManager;

public class GameClearScreen extends BaseScreen {
	private Stage gameClearStage;
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private MusicManager musicManager;

	@Override
	public void render(float delta) {
		super.render(delta);
		gameClearStage.draw();
		gameClearStage.act();
	}

	@Override
	public void show() {
		gameClearStage = stageFactory.makeStage(StageEnum.GAME_CLEAR);
		Gdx.input.setInputProcessor(gameClearStage);
		musicManager.stopMusic();
		soundManager.setSoundByPathAndPlay("game_over");
	}
}
