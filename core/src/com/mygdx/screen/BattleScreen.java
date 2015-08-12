package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.StorySectionManager;

public class BattleScreen extends BaseScreen {
	@Autowired
	private AnimationManager animationManager;
	@Autowired
	private StorySectionManager storySectionManager;
	private Stage gameUiStage, characterUiStage, monsterStage, battleStage;

	public BattleScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		monsterStage.draw();
		characterUiStage.draw();
		// FIXME GameUi와 CharacherUi의 분리가 필요
		gameUiStage.draw();
		battleStage.draw();

		// Animation이 진행중일때는 사용자의 입력에 대한 행동을 수행하지 않음
		battleStage.act(delta);
		monsterStage.act(delta);
		characterUiStage.act(delta);
		gameUiStage.act();
	}

	@Override
	public void resize(int width, int height) {
		// gameUiStage.getViewport().update(width, height, false);
		// characterUiStage.getViewport().update(width, height, false);
		// /*
		// * monsterStage.setViewport(new FitViewport(width, height,
		// monsterStage
		// * .getCamera()));
		// */
		// // monsterStage.getViewport().update(width, height, false);
		// battleStage.getViewport().update(width, height, false);
	}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);
		battleStage = stageFactory.makeBattleStage();

		setInputProcessor();
		// musicManager.setBattleMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, characterUiStage);
		multiplexer.addProcessor(2, monsterStage);
		multiplexer.addProcessor(3, battleStage);

		Gdx.input.setInputProcessor(multiplexer);
	}
}
