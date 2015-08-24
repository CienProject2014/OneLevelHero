package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BattleScreen extends BaseScreen {
	private Stage gameUiStage, characterUiStage, monsterStage, battleStage, skillStage;
	public static boolean showSkillStage = false;

	public BattleScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		monsterStage.draw();
		characterUiStage.draw();
		battleStage.draw();
		// FIXME GameUi와 CharacherUi의 분리가 필요
		gameUiStage.draw();

		if (showSkillStage) {
			skillStage.draw();
		}
		// Animation이 진행중일때는 사용자의 입력에 대한 행동을 수행하지 않음
		monsterStage.act(delta);
		characterUiStage.act(delta);
		gameUiStage.act();
		battleStage.act(delta);
		skillStage.act();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);
		battleStage = stageFactory.makeStage(StageEnum.BATTLE);
		skillStage = stageFactory.makeStage(StageEnum.SKILL);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
		// musicManager.setBattleMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (showSkillStage) {
			multiplexer.addProcessor(0, skillStage);

		} else {
			multiplexer.addProcessor(0, gameUiStage);
			multiplexer.addProcessor(1, characterUiStage);
			multiplexer.addProcessor(2, monsterStage);
			multiplexer.addProcessor(3, battleStage);
			multiplexer.addProcessor(4, skillStage);
		}
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
