package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.SoundManager;

public class GameOverScreen extends BaseScreen {
	private Stage gameOverStage;
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void render(float delta) {
		super.render(delta);
		gameOverStage.draw();
		gameOverStage.act();
	}

	@Override
	public void show() {
		gameOverStage = stageFactory.makeStage(StageEnum.GAME_OVER);
		Gdx.input.setInputProcessor(gameOverStage);
		soundManager.setSoundByPathAndPlay("game_over");
	}
}
